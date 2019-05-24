package com.jiefeng.ssm.service;

import com.jiefeng.ssm.bean.Admin;
import com.jiefeng.ssm.bean.User;
import com.jiefeng.ssm.dto.RegisterExecution;

public interface RegisterService {

    /**
     * 生成验证码并且放到Redis中，key为email:verifyCode 并且设置过期时间为5分钟
     * @return
     */
    RegisterExecution generateVerifyCode(String email,String username);


    /**
     *
     * @param email
     * @param verifyCodeToken
     * @return
     */
    RegisterExecution validationVerifyCode(String email,String verifyCodeToken);

    /**
     * 注册用户
     * @param user
     * @return
     */
    RegisterExecution addUser( User user);


    RegisterExecution addAdmin(Admin admin);


}
