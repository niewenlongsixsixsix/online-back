package com.jiefeng.ssm.service;

import com.jiefeng.ssm.bean.Admin;

import java.util.List;

public interface AdminService {

    /**
     * 获取所有的管理员信息
     * @return
     */
    List<Admin> getAllAdmin();

    boolean updateAdminInfo(Admin admin);


    Admin getAdminByPrimaryKey(Integer id);

}
