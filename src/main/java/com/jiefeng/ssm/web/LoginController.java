package com.jiefeng.ssm.web;

import com.jiefeng.ssm.bean.LoginAccount;
import com.jiefeng.ssm.dao.LoginAccountDao;
import com.jiefeng.ssm.util.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/account")
public class LoginController {

    @Autowired
    private LoginAccountDao loginAccountDao;

    @RequestMapping(value = "/insertAccount",method = RequestMethod.POST)
    @ResponseBody
    public boolean insertAccount(LoginAccount account){
        String username = account.getUsername();
        String password = account.getPassword();

        //对密码进行MD5加密
        String passwordByMD5 = MD5Util.Md5Encryption(password, username);

        //将用户名和加密后的密码存放到bean中
        account.setPassword(passwordByMD5);

        //添加账户
        boolean result = loginAccountDao.insertLoginAccount(account);

        return result;
    }


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(LoginAccount account){

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(account.getUsername(),account.getPassword());
        token.setRememberMe(true);

        try {
            subject.login(token);

        } catch (AuthenticationException e) {
           throw e;
        }

        return "Main";
    }

    @RequestMapping("/logout")
    @ResponseBody
    public void logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }


}
