package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.bean.LoginAccount;

public interface LoginAccountDao {

    /**
     * 添加一个登录账号
     * @return
     */
    boolean insertLoginAccount(LoginAccount record);


    /**
     * 根据用户名获取其密码用于登录校验
     * @param username
     * @return
     */
    LoginAccount getAccountByUsername(String username);

}
