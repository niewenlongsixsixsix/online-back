package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.BaseTest;
import com.jiefeng.ssm.bean.PermissionCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PermissionCategoryDaoTest extends BaseTest {

    @Autowired
    private PermissionCategoryDao permissionCategoryDao;

    @Test
    public void testGetAllPermission(){
        List<PermissionCategory> allPermissonCategory = permissionCategoryDao.getAllPermissonCategory();
        System.out.println(allPermissonCategory);
    }
}
