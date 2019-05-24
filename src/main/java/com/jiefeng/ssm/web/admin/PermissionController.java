package com.jiefeng.ssm.web.admin;

import com.jiefeng.ssm.annotation.LoggerOrException;
import com.jiefeng.ssm.bean.PermissionCategory;
import com.jiefeng.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/getAllPermissionByCategory",method = RequestMethod.GET)
    @ResponseBody
    @LoggerOrException(operationName = "获取所有权限")
    public Map<String,Object> getAllPermissionByCategory(){
        Map<String,Object> modelMap = new HashMap<>();
        List<PermissionCategory> permissionList = permissionService.getAllPermissionByCategory();
        if(!permissionList.isEmpty()){
            modelMap.put("success",true);
            modelMap.put("permissionList",permissionList);
        }else{
            modelMap.put("success",false);
            //返回出错误信息
            modelMap.put("errMsg","系统错误");
        }
        return modelMap;
    }

    @RequestMapping(value = "/getPermissionByRoleId/{roleId}",method = RequestMethod.GET)
    @ResponseBody
    @LoggerOrException(operationName = "查询角色的权限")
    public Map<String,Object> getPermissionByRoleId(@PathVariable Integer roleId) throws Exception {
        Map<String,Object> modelMap = new HashMap<>();

        if(roleId == null){
            throw new Exception("参数错误");
        }

        List<Integer> permissionByRoleId = permissionService.getPermissionByRoleId(roleId);

        modelMap.put("success",true);
        modelMap.put("permissionList",permissionByRoleId);

        return modelMap;
    }


    @RequestMapping(value = "/getPermissionByAdminId/{adminId}",method = RequestMethod.GET)
    @ResponseBody
    @LoggerOrException(operationName = "查询角色的权限")
    public Map<String,Object> getPermissionByAdminId(@PathVariable Integer adminId) throws Exception {
        Map<String,Object> modelMap = new HashMap<>();

        if(adminId == null){
            throw new Exception("参数错误");
        }

        List<Integer> permissionByRoleId = permissionService.getPermissionByAdminId(adminId);

        modelMap.put("success",true);
        modelMap.put("adminPermissionList",permissionByRoleId);

        return modelMap;
    }

}
