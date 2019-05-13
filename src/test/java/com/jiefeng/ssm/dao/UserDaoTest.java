package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.BaseTest;
import com.jiefeng.ssm.bean.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserDaoTest extends BaseTest {

    @Autowired
    private UserDao userDao;


    @Test
    public void testGetPassword(){
        User admin = userDao.getPasswordByUsername("admin");
        System.out.println(admin);
    }

    @Test
    public void testGetAllUser(){
        List<User> allUsers = userDao.getAllUsers();
        System.out.println(allUsers);
    }
}
