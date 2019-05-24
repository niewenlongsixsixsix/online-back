package com.jiefeng.ssm.web.admin;


import com.jiefeng.ssm.annotation.LoggerOrException;
import com.jiefeng.ssm.bean.User;
import com.jiefeng.ssm.beanExtend.UserExtend;
import com.jiefeng.ssm.beanExtend.UserOrAdminType;
import com.jiefeng.ssm.dto.UserExecution;
import com.jiefeng.ssm.enums.UserStateEnums;
import com.jiefeng.ssm.service.UserService;
import com.jiefeng.ssm.util.ImageUtil;
import com.jiefeng.ssm.util.MD5Util;
import com.jiefeng.ssm.util.PathUtil;
import com.jiefeng.ssm.util.SaltUtil;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户数据
     * @return
     */
    @RequestMapping(value = "/getAllUser/{type}",method = RequestMethod.GET)
    @ResponseBody
    @LoggerOrException(operationName = "获取所有用户信息")
    public Map<String,Object> getAllUser(@PathVariable Integer type) throws Exception {

        Map<String,Object> modelMap = new HashMap<>();

        UserExecution userDto = userService.getAllUser(type);

        //判断是否正常返回
        if(userDto.getState() == UserStateEnums.SUCCESS.getState()){
            modelMap.put("success",true);
            modelMap.put("userList",userDto.getUserList());
            modelMap.put("describe","查询全部用户信息");
        }else{
            throw new Exception(userDto.getStateInfo());
        }
        return modelMap;
    }

    /**
     * 获取用户数据
     * @return
     */
    @RequestMapping(value = "/getUserInfoByUserId/{userId}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getUserInfoByUserId(@PathVariable Integer userId)  {

        Map<String,Object> modelMap = new HashMap<>();

        User userByPrimaryKey = userService.getUserByPrimaryKey(userId);
        if(userByPrimaryKey != null){
            modelMap.put("success",true);
            modelMap.put("userInfo",userByPrimaryKey);
        }
        return modelMap;
    }

    /**
     * 重置用户的密码
     * @param userId
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/resetPassword/{userId}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> resetPassword(@PathVariable Integer userId) throws UnsupportedEncodingException {
        Map<String,Object> modelMap = new HashMap<>();

        //将基本用户信息填充到bean中
        User user = new User();
        //设置要重置密码的用户id
        user.setId(userId);
        //获取盐
        String salt = SaltUtil.generateSalt();
        //生成初始密码123456
        user.setPassword(MD5Util.Md5Encryption("123456",salt));
        //将盐值设置到bean中
        user.setSalt(salt);

        boolean b = userService.updateUserInfo(user);
        if(b){
            modelMap.put("success",true);
        }else{
            modelMap.put("success",false);
        }

        return modelMap;
    }

    /**
     * 用户修改密码
     * @param map
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/userChangePassword",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> userChangePassword(@RequestBody Map map) throws UnsupportedEncodingException {
        Map<String,Object> modelMap = new HashMap<>();

        try{
            String oldPassword = (String) map.get("oldPassword");
            String newPassword = (String) map.get("newPassword");
            User user = new User();

            //获取当前用户的登录主体
            UserOrAdminType principal = (UserOrAdminType) SecurityUtils.getSubject().getPrincipal();

            //获取登录用户信息
            UserExtend currentLoginUser = (UserExtend) principal.getObject();

            //获取登录用户的id
            Integer userId = currentLoginUser.getId();

            //查询当前用户的所有信息
            User currentLogin = userService.getUserByPrimaryKey(userId);

            //拿到加密密码
            String currentLoginPassword = currentLogin.getPassword();

            //将用户输入的旧密码进行加密
            String newPasswordMd5 = MD5Util.Md5Encryption(oldPassword, currentLogin.getSalt());

            //对比密码
            if(currentLoginPassword.equals(newPasswordMd5)){
                String salt = SaltUtil.generateSalt();
                String userNewPassword = MD5Util.Md5Encryption(newPassword,salt);

                user.setPassword(userNewPassword);
                user.setSalt(salt);
                user.setId(userId);

                userService.updateUserInfo(user);
                modelMap.put("success",true);
            }else{
                modelMap.put("success",false);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        return modelMap;
    }

    /**
     * 获取当前用户头像
     * @return
     */
    @RequestMapping(value = "/getAvatar",method = RequestMethod.GET)
    @ResponseBody
    public String getCurrentUser(){
        //获取当前用户的登录主体
        UserOrAdminType principal = (UserOrAdminType) SecurityUtils.getSubject().getPrincipal();

        //获取登录用户信息
        UserExtend currentLoginUser = (UserExtend) principal.getObject();

        //获取登录用户的id
        Integer userId = currentLoginUser.getId();

        //查询当前用户的所有信息
        User currentLogin = userService.getUserByPrimaryKey(userId);

        return currentLogin.getAvatar();
    }

    /**
     * 更改用户状态
     * @param userId
     * @return
     */
    @RequiresRoles("ADMIN")
    @RequestMapping(value = "/changeUserStatus/{userId}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> changeUserStatus(@PathVariable Integer userId){
        Map<String,Object> modelMap = new HashMap<>();


        Subject currentSubject = SecurityUtils.getSubject();

        //先从数据库中拿出用户的基本数据
        User databaseUserInfo = userService.getUserByPrimaryKey(userId);

        //获取当前用户账号的状态
        Integer status = databaseUserInfo.getStatus();

        //声明一个要存放更新用户的bean
        User updateUser = new User();

        //将用户ID设置到里面
        updateUser.setId(userId);

        //反转用户账号状态
        if(status == 0){
            updateUser.setStatus(1);
        }else{
            updateUser.setStatus(0);
        }
        //进行更新
        boolean b = userService.updateUserInfo(updateUser);

        if(b){
            modelMap.put("success",true);
        }else{
            modelMap.put("success",false);
        }

        return modelMap;
    }

    @RequestMapping(value = "/changeHeadImg",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> changeHeadImg(HttpServletRequest request) throws IOException {

        Map<String,Object> modelMap = new HashMap<>();

        User user = new User();

        UserOrAdminType principal = (UserOrAdminType) SecurityUtils.getSubject().getPrincipal();

        UserExtend currentLoginUser = (UserExtend) principal.getObject();

        Integer userId = currentLoginUser.getId();

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> files = multipartRequest.getFiles("userHeadImg");


        try{
            String path = PathUtil.generateUserHeadImg(userId);
            String imgPath = ImageUtil.generateNormalImg(files.get(0), path);

            user.setId(userId);
            user.setAvatar(imgPath);
            userService.updateUserInfo(user);
        }catch (IOException e){
            throw e;
        }

        modelMap.put("success",true);

        return modelMap;
    }
}
