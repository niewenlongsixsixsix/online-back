package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.BaseTest;
import com.jiefeng.ssm.bean.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class UserDaoTest extends BaseTest {

    @Autowired
    private UserDao userDao;


    @Test
    public void testGetPassword(){
        User admin = userDao.getPasswordByEmail("oldyoghrout@gmail.com");
        System.out.println(admin);
    }


    @Test
    public void testAddUser(){
        User user = new User();
        user.setUsername("赵松");
        user.setPassword("a66abb5684c45962d887564f08346e8d");
        user.setPhone("15296650703");
        user.setEmail("17696155307@163.com");
        user.setRegisterTime(new Date());

        boolean b = userDao.addUser(user);
        Assert.assertEquals(true,b);
    }

    @Test
    public void testGetAllUser(){
        List<User> allUsers = userDao.getAllUsers(1);
        System.out.println(allUsers);
    }

    @Test
    public void updateUserInfo(){
        User user = new User();
        user.setId(1540730);
        user.setSalt("admin");
        boolean b = userDao.updateUserInfo(user);
        System.out.println(b);
    }
}
