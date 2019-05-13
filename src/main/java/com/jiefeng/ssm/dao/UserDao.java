package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.bean.User;

import java.util.List;

public interface UserDao {


    /**
     * 用户登录
     * @param username
     * @return
     */
    User getPasswordByUsername(String username);


    /**
     * 获取所有的用户信息
     * @return
     */
    List<User> getAllUsers();

}
