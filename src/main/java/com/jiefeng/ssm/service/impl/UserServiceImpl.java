package com.jiefeng.ssm.service.impl;

import com.jiefeng.ssm.bean.User;
import com.jiefeng.ssm.dao.UserDao;
import com.jiefeng.ssm.dto.UserDto;
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
    public UserDto getAllUser() {

        //初始化
        List<User> allUsers = null;

        //判断是否能够正常取到用户数据
        try{
             allUsers = userDao.getAllUsers();
        }catch (Exception e){
            e.printStackTrace();
            return new UserDto(UserStateEnums.SYSTEM_ERROR);
        }
        //正常取到，返回
        return new UserDto(UserStateEnums.SUCCESS,allUsers);
    }

}
