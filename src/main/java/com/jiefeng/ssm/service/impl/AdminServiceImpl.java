package com.jiefeng.ssm.service.impl;

import com.jiefeng.ssm.bean.Admin;
import com.jiefeng.ssm.dao.AdminDao;
import com.jiefeng.ssm.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AdminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public List<Admin> getAllAdmin() {
        return adminDao.getAllAdmin();
    }

    @Override
    public boolean updateAdminInfo(Admin admin) {
        return adminDao.updateAdmin(admin);
    }

    @Override
    public Admin getAdminByPrimaryKey(Integer id) {
        return adminDao.getAdminByPrimaryKey(id);
    }

}
