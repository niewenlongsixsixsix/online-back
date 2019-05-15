package com.jiefeng.ssm.service;

import com.jiefeng.ssm.bean.User;
import com.jiefeng.ssm.dto.LoginExecution;

public interface LoginService {

    /**
     * 进行登录验证
     * @param user
     * @return
     */
    LoginExecution login(User user);

}
