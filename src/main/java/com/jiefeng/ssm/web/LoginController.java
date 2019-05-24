package com.jiefeng.ssm.web;

import com.jiefeng.ssm.annotation.ArchivesLog;
import com.jiefeng.ssm.bean.Admin;
import com.jiefeng.ssm.bean.User;
import com.jiefeng.ssm.beanExtend.UserExtend;
import com.jiefeng.ssm.beanExtend.UserOrAdminType;
import com.jiefeng.ssm.dto.LoginExecution;
import com.jiefeng.ssm.enums.LoginStateEnums;
import com.jiefeng.ssm.redis.JedisUtil;
import com.jiefeng.ssm.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @Autowired
    private JedisUtil.Strings strings;

    /**
     * 登录验证
     * @param map
     * @return
     */
    @RequestMapping(value = "/AccountAuth/{type}",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> AccountAuth(@PathVariable Integer type,@RequestBody Map map) throws Exception {

        Map<String,Object> modelMap = new HashMap<>();

        String username = (String) map.get("username");
        String password = (String)map.get("password");

        logger.info("用户名: " +username + "密码: " + password);

        LoginExecution loginDto = loginService.login(type,new User(username, password));

        if(loginDto.getState() == LoginStateEnums.SUCCESS.getState()){
            UserOrAdminType subject = (UserOrAdminType) SecurityUtils.getSubject().getPrincipal();
            if(subject.getType() == 0){
                UserExtend object = (UserExtend) subject.getObject();
                modelMap.put("type",object.getType());
                modelMap.put("userId",object.getId());
                modelMap.put("avatar",object.getAvatar());
            }else{
                Admin admin = (Admin) subject.getObject();
                modelMap.put("userId",admin.getId());
            }
            modelMap.put("success",true);
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg",loginDto.getStateInfo());
        }
        return modelMap;
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> logout(){
        Map<String,Object> modelMap = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();

        subject.logout();

        modelMap.put("success",true);

        return modelMap;
    }

}
