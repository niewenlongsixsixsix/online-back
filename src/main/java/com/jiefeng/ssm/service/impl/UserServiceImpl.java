package com.jiefeng.ssm.service.impl;

import com.jiefeng.ssm.bean.User;
import com.jiefeng.ssm.dao.UserDao;
import com.jiefeng.ssm.dto.UserExecution;
import com.jiefeng.ssm.enums.UserStateEnums;
import com.jiefeng.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 获取所有的用户数据
     * @return
     */
    @Override
    public UserExecution getAllUser(Integer type) {

        //初始化
        List<User> allUsers = null;

        //判断是否能够正常取到用户数据
        try{
             allUsers = userDao.getAllUsers(type);
        }catch (Exception e){
            e.printStackTrace();
            return new UserExecution(UserStateEnums.SYSTEM_ERROR);
        }
        //正常取到，返回
        return new UserExecution(UserStateEnums.SUCCESS,allUsers);
    }

    @Override
    public User getUserByPrimaryKey(Integer userId) {
        return userDao.getUserByPrimaryKey(userId);
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @Override
    public boolean updateUserInfo(User user) {
        return userDao.updateUserInfo(user);
    }

}
