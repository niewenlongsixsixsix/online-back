package com.jiefeng.ssm.web.loginFront;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginFront {

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "Login";
    }
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(){
        return "Register";
    }
    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String main(){
        return "Main";
    }
}
