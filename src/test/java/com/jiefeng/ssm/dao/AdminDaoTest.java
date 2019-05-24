package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.BaseTest;
import com.jiefeng.ssm.bean.Admin;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminDaoTest extends BaseTest {

    @Autowired
    private AdminDao adminDao;

    /**
     *
     */
    @Test
    public void testUpdateAdminInfo(){
        Admin admin = new Admin();
        admin.setId(1975);
        admin.setName("caojiguanli");
        admin.setStatus(0);
        boolean b = adminDao.updateAdmin(admin);
        System.out.println(b);


    }
}
