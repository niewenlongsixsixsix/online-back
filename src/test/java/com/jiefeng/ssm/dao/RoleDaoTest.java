package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.BaseTest;
import com.jiefeng.ssm.bean.Role;
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

    @Test
    public void testGEtRoleByPrimaryKey(){
        Role roleByPrimaryKey = roleDao.getRoleByPrimaryKey(1);
        System.out.println(roleByPrimaryKey);
    }
}
