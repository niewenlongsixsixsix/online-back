package com.jiefeng.ssm.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/getUserByUsername",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getUserByUsername(){
        Map<String,Object> modelMap = new HashMap<>();
        logger.info("Enter");
        modelMap.put("nie","wenlong");

        return modelMap;
    }


    @RequestMapping("/login")
    public String login(){
        return "Login";
    }
}
