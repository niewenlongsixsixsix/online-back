package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.bean.User;
import org.apache.ibatis.annotations.Param;

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
    List<User> getAllUsers(Integer type);

    /**
     * 添加用户
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     * 根据用户ID获取用户信息
     * @param userId
     * @return
     */
    User getUserByPrimaryKey(Integer userId);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    boolean updateUserInfo(User user);


}
