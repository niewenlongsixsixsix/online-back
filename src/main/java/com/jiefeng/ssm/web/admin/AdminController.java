package com.jiefeng.ssm.web.admin;

import com.jiefeng.ssm.annotation.LoggerOrException;
import com.jiefeng.ssm.bean.Admin;
import com.jiefeng.ssm.bean.Role;
import com.jiefeng.ssm.bean.User;
import com.jiefeng.ssm.dto.RegisterExecution;
import com.jiefeng.ssm.enums.RegisterStateEnums;
import com.jiefeng.ssm.service.AdminService;
import com.jiefeng.ssm.service.RegisterService;
import com.jiefeng.ssm.service.RoleService;
import com.jiefeng.ssm.util.MD5Util;
import com.jiefeng.ssm.util.SaltUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.ObjectUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("admin")
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RegisterService registerService;

    /**
     * 查询所有管理员信息
     * @return
     */
    @RequestMapping(value = "getAllAdmin",method = RequestMethod.GET)
    @ResponseBody
    @LoggerOrException(operationName = "查询所有管理员信息")
    public  Map<String,Object> getAllAdmin(){
        Map<String,Object> modelMap = new HashMap<>();
        try {
            List<Admin> allAdmin = adminService.getAllAdmin();
            modelMap.put("success",true);
            modelMap.put("adminList",allAdmin);
        } catch (Exception e) {
           throw e;
        }
        return modelMap;
    }

    /**
     * 添加或者修改管理员信息
     * @param type
     * @param adminId
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"addAdmin/{type}","updateAdminInfo/{type}/{adminId}"},method = RequestMethod.POST)
    @ResponseBody
    @LoggerOrException(operationName = "添加或者修改管理员信息")
    public Map<String,Object> addOrUpdateAdminInfo(@PathVariable Integer type, @PathVariable(required = false) Integer adminId, @RequestBody Map map) throws Exception {
        Map<String,Object> modelMap = new HashMap<>();

        JSONObject object = JSONObject.fromObject(map.get("adminInfo"));
        Admin adminInfo = (Admin) JSONObject.toBean(object,Admin.class);
        System.out.println(adminInfo);

        //获取选中的角色
        Integer roleId = (Integer) map.get("roleId");

        if(type == null){
            throw new Exception("参数错误");
        }

        Subject subject = SecurityUtils.getSubject();

        // 1添加账号  0 更新管理员信息
        if(type == 1){
            if(!subject.isPermitted("ADMIN:CREATE")){
                throw new Exception("权限不够");
            }

            adminInfo.setStatus(1);
            //生成默认密码123456
            adminInfo.setPassword("123456");
            adminInfo.setCreateTime(new Date());
            String salt = SaltUtil.generateSalt();

            //生成加盐密码并将其设置到bean中
            adminInfo.setPassword(MD5Util.Md5Encryption(adminInfo.getPassword(),salt));
            //将salt添加到bean中
            adminInfo.setSalt(salt);
            adminInfo.setType(1);
            adminInfo.setRole(new Role(roleId));
            RegisterExecution registerExecution = registerService.addAdmin(adminInfo);
            if(registerExecution.getState() == RegisterStateEnums.SUCCESS.getState()){

                System.out.println("admin primaryKey: " + adminInfo.getId());
                //为管理员添加角色
                boolean b = roleService.addRoleForUserOrAdmin(adminInfo.getId(), roleId);
                if(b){
                    modelMap.put("success",true);
                }
            }
        }else{
            if(!SecurityUtils.getSubject().hasRole("ADMIN")){
                throw new Exception("权限不够");
            }
            adminInfo.setId(adminId);
            adminInfo.setRole(new Role(roleId));
            adminInfo.setStatus(null);
            boolean b = adminService.updateAdminInfo(adminInfo);
            boolean b1 = roleService.updateRoleForUserOrAdmin(adminInfo.getId(), roleId);
            if(b && b1){
                modelMap.put("success",true);
            }
        }


        return modelMap;

    }


    @RequestMapping(value = {"changeAdminStatus/{id}"},method = RequestMethod.GET)
    @ResponseBody
    @LoggerOrException(operationName = "激活或禁用管理员账号")
    public Map<String,Object> changeAdminStatus(@PathVariable Integer id){

        Map<String,Object> modelMap = new HashMap<>();

        Admin oldAdmin = adminService.getAdminByPrimaryKey(id);

        Integer status = oldAdmin.getStatus();

        Admin updateAdmin = new Admin();

        updateAdmin.setId(id);

        if(status == 0){
            updateAdmin.setStatus(1);
        }else{
            updateAdmin.setStatus(0);
        }
        boolean b = adminService.updateAdminInfo(updateAdmin);

        if(b){
            modelMap.put("success",true);
        }else{
            modelMap.put("success",false);
        }

        return modelMap;

    }
}
