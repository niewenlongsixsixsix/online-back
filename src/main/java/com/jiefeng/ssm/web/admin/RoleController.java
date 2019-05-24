package com.jiefeng.ssm.web.admin;

import com.jiefeng.ssm.annotation.LoggerOrException;
import com.jiefeng.ssm.bean.Admin;
import com.jiefeng.ssm.bean.Role;
import com.jiefeng.ssm.dto.RegisterExecution;
import com.jiefeng.ssm.enums.RegisterStateEnums;
import com.jiefeng.ssm.service.RoleService;
import com.jiefeng.ssm.util.MD5Util;
import com.jiefeng.ssm.util.PermissionListFilterUtil;
import com.jiefeng.ssm.util.SaltUtil;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("role")
public class RoleController {

    Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;


    @RequestMapping(value = "/getAllRole",method = RequestMethod.GET)
    @ResponseBody
    @LoggerOrException(operationName = "获取所有角色")
    public Map<String,Object> getAllRole(){
        Map<String,Object> modelMap = new HashMap<>();

        List<Role> allRole = roleService.getAllRole();
        if(!allRole.isEmpty()){
            modelMap.put("success",true);
            modelMap.put("roleList",allRole);
            modelMap.put("describe","查询全部角色信息");
        }else{
            modelMap.put("success",false);
            //返回出错误信息
            modelMap.put("errMsg","系统错误");
        }
        return modelMap;
    }


    @RequestMapping(value = "updatePermissionForRole/{roleId}",method = RequestMethod.POST)
    @ResponseBody
    @LoggerOrException(operationName = "更新角色所拥有的权限")
    @Transactional
    public Map<String,Object> updatePermissionForRole(@PathVariable Integer roleId, @RequestBody Map map) throws Exception {
        Map<String,Object> modelMap = new HashMap<>();

        if(roleId == null){
            throw new Exception("参数为空");
        }

        //获取前端传过来的permissionList数组
        List<Integer> permissionList = (List<Integer>) map.get("permissionList");

        boolean b = roleService.deleteAllPermissionByRoleId(roleId);
        if(b){
            //处理一下前端传过来的权限数组，把其中的权限分类去除掉
            boolean b1 = roleService.batchAddPermissionForRole(roleId, PermissionListFilterUtil.permissionListFilter(permissionList));
            if(!b1){
                throw new RuntimeException("更新角色权限失败");
            }
        }else{
            throw new RuntimeException("更新角色权限失败");
        }

        logger.info("permissionList: " + permissionList);

        modelMap.put("success",true);

        return modelMap;
    }



    @RequestMapping(value = {"addRole/{type}","updateRoleInfo/{type}/{roleId}"},method = RequestMethod.POST)
    @ResponseBody
    @LoggerOrException(operationName = "添加或者修改管理员信息")
    public Map<String,Object> addOrUpdateRoleInfo(@PathVariable Integer type, @PathVariable(required = false) Integer roleId, @RequestBody Map map) throws Exception {
        Map<String,Object> modelMap = new HashMap<>();

        JSONObject object = JSONObject.fromObject(map.get("RoleInfo"));
        Role roleInfo = (Role) JSONObject.toBean(object,Role.class);
        System.out.println(roleInfo);

        if(type == null){
            throw new Exception("参数错误");
        }

        // 1添加账号  0 更新管理员信息
        if(type == 1){
            roleInfo.setCreateTime(new Date());
            boolean b = roleService.addRole(roleInfo);
            if(b){
                modelMap.put("success",true);
            }
        }else{
            roleInfo.setId(roleId);
            boolean b = roleService.updateRole(roleInfo);
            if(b){
                modelMap.put("success",true);
            }
        }

        return modelMap;

    }
}
