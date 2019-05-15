package com.jiefeng.ssm.web.admin;


import com.jiefeng.ssm.dto.UserExecution;
import com.jiefeng.ssm.enums.UserStateEnums;
import com.jiefeng.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
    @RequestMapping(value = "/getAllUser",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAllUser(HttpServletRequest request){

        Map<String,Object> modelMap = new HashMap<>();

        UserExecution userDto = userService.getAllUser();

        //判断是否正常返回
        if(userDto.getState() == UserStateEnums.SUCCESS.getState()){
            modelMap.put("success",true);
            modelMap.put("userList",userDto.getUserList());
            modelMap.put("describe","查询全部用户信息");
        }else{
            modelMap.put("success",false);
            //返回出错误信息
            modelMap.put("errMsg",userDto.getStateInfo());
        }

        return modelMap;
    }
}
