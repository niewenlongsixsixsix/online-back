package com.jiefeng.ssm.service;

import com.jiefeng.ssm.bean.User;
import com.jiefeng.ssm.dto.LoginDto;

public interface LoginService {

    /**
     * 进行登录验证
     * @param user
     * @return
     */
    LoginDto login(User user);

}
