package com.jiefeng.ssm.web;

import com.jiefeng.ssm.bean.Admin;
import com.jiefeng.ssm.bean.User;
import com.jiefeng.ssm.dto.RegisterExecution;
import com.jiefeng.ssm.enums.RegisterStateEnums;
import com.jiefeng.ssm.service.RegisterService;
import com.jiefeng.ssm.util.MD5Util;
import com.jiefeng.ssm.util.SaltUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/register")
public class RegisterController {

    Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private RegisterService registerService;

    /**
     * 获取验证码
     * @param email
     * @param username
     * @return
     */
    @RequestMapping(value = "/getVerifyCode",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getVerifyCode(@RequestParam String email, @RequestParam String username){

        Map<String,Object> modelMap = new HashMap<>();

        //判空
        if(email.isEmpty() || username.isEmpty()){
            modelMap.put("success",false);
            modelMap.put("errMsg","邮箱或者用户名为空");
            return modelMap;
        }

        logger.info("email: " + email + " username: " + username);

        //生成验证码并返回对应结果
        RegisterExecution execution = registerService.generateVerifyCode(email, username);

        //判断结果
        if(execution.getState() == RegisterStateEnums.SUCCESS.getState()){
            modelMap.put("success",true);
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg",execution.getStateInfo());
        }
        return modelMap;
    }

    /**
     * 验证验证码是否正确
     * @param email
     * @param code
     * @return
     */
    @RequestMapping(value = "/validationCode",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> validationCode(@RequestParam String email,@RequestParam String code){
        Map<String,Object> modelMap = new HashMap<>();

        //判空
        if(email.isEmpty() || code.isEmpty()){
            modelMap.put("success",false);
            modelMap.put("errMsg","邮箱或者验证码为空");
            return modelMap;
        }

        logger.info("验证码: " + code);

        RegisterExecution execution = registerService.validationVerifyCode(email, code);

        if(execution.getState() == RegisterStateEnums.SUCCESS.getState()){
            modelMap.put("success",true);
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg",execution.getStateInfo());
        }

        return modelMap;
    }

    /**
     * 添加用户到数据库
     * @param map
     * @return
     */
    @RequestMapping(value = "addUser",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addUser(@RequestBody Map map){
        Map<String,Object> modelMap = new HashMap<>();

        //先打印从前端发送过来的用户数据
        logger.info("map: " + map.toString());
        //获取其中register的用户数据
        Object register = map.get("register");
        //获取携带的验证码
        String verifyCode = (String) map.get("verifyCode");
        //打印验证码
        logger.info("register code: " + verifyCode);

        try{
            //先将JSON字符串转化为JSON对象
            JSONObject object = JSONObject.fromObject(register);
            //将JSON对象转化为对应的bean
            User bean = (User) JSONObject.toBean(object, User.class);
            logger.info("toBean User: " + bean);
            //生成密码的盐
            String salt = SaltUtil.generateSalt();
            logger.info("salt: " + salt);
            //生成加盐密码并将其设置到bean中
            bean.setPassword(MD5Util.Md5Encryption(bean.getPassword(),salt));
            //将salt添加到bean中
            bean.setSalt(salt);
            //注册时间
            bean.setRegisterTime(new Date());

            //激活账号
            bean.setStatus(1);
            //添加用户
            RegisterExecution execution = registerService.addUser(bean);

            if(execution.getState() == RegisterStateEnums.SUCCESS.getState()){
                modelMap.put("success",true);
            }else{
                modelMap.put("success",false);
                modelMap.put("errMsg",execution.getStateInfo());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelMap;
    }



    /**
     * 添加用户到数据库
     * @param map
     * @return
     */
    @RequestMapping(value = "addAdmin",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addAdmin(@RequestBody Map map){
        Map<String,Object> modelMap = new HashMap<>();

        //先打印从前端发送过来的用户数据
        logger.info("map: " + map.toString());
        //获取其中register的用户数据
        Object register = map.get("adminRegister");
        try{
            //先将JSON字符串转化为JSON对象
            JSONObject object = JSONObject.fromObject(register);
            //将JSON对象转化为对应的bean
            Admin bean = (Admin) JSONObject.toBean(object, Admin.class);
            logger.info("toBean User: " + bean);
            //生成密码的盐
            String salt = SaltUtil.generateSalt();
            logger.info("salt: " + salt);
            //生成加盐密码并将其设置到bean中
            bean.setPassword(MD5Util.Md5Encryption(bean.getPassword(),salt));
            //将salt添加到bean中
            bean.setSalt(salt);
            //注册时间
            bean.setCreateTime(new Date());
            //添加用户
            RegisterExecution execution = registerService.addAdmin(bean);

            if(execution.getState() == RegisterStateEnums.SUCCESS.getState()){
                modelMap.put("success",true);
            }else{
                modelMap.put("success",false);
                modelMap.put("errMsg",execution.getStateInfo());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelMap;
    }
}
