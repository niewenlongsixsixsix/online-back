package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.bean.Admin;

import java.util.List;

public interface AdminDao {

    List<Admin> getAllAdmin();


    Admin getAdminByUsername(String username);

    boolean addAdmin(Admin admin);


    boolean updateAdmin(Admin admin);

    Admin getAdminByPrimaryKey(Integer id);

}
