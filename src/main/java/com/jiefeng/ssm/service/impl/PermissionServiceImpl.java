package com.jiefeng.ssm.service.impl;

import com.jiefeng.ssm.bean.PermissionCategory;
import com.jiefeng.ssm.dao.PermissionCategoryDao;
import com.jiefeng.ssm.dao.PermissionDao;
import com.jiefeng.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PermissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionCategoryDao permissionCategoryDao;

    @Autowired
    private PermissionDao permissionDao;


    /**
     * 根据权限分组获取其下的权限
     * @return
     */
    @Override
    public List<PermissionCategory> getAllPermissionByCategory() {
        return permissionCategoryDao.getAllPermissonCategory();
    }

    /**
     * 根据角色ID获取其下所有的权限
     * @param roleId
     * @return
     */
    @Override
    public List<Integer> getPermissionByRoleId(Integer roleId) {
        return permissionDao.getPermissionByRoleId(roleId);
    }

    @Override
    public List<Integer> getPermissionByAdminId(Integer adminId) {
        return permissionDao.getPermissionByAdminId(adminId);
    }
}
