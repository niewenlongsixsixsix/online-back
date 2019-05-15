package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.bean.User;

import java.util.List;

public interface UserDao {


    /**
     * 用户登录
     * @param email
     * @return
     */
    User getPasswordByEmail(String email);


    /**
     * 获取所有的用户信息
     * @return
     */
    List<User> getAllUsers();

    /**
     * 添加用户
     * @param user
     * @return
     */
    boolean addUser(User user);

}
