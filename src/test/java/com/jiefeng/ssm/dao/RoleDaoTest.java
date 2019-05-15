package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoleDaoTest extends BaseTest {

    @Autowired
    private RoleDao roleDao;

    @Test
    public void testGetUserRoles(){
        List<String> userAllRole = roleDao.getUserAllRole(1540735);

        System.out.println(userAllRole);
    }
}
