package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PermissionDaoTest extends BaseTest {

    @Autowired
    private PermissionDao permissionDao;

    @Test
    public void testGetUserPermissions(){
        List<String> userAllRole = permissionDao.getUserAllPermission(1540735);

        System.out.println(userAllRole);
    }
}
