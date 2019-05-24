package com.jiefeng.ssm.service.impl;

import com.jiefeng.ssm.bean.Role;
import com.jiefeng.ssm.dao.PermissionDao;
import com.jiefeng.ssm.dao.RoleDao;
import com.jiefeng.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("RoleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Role> getAllRole() {
        return roleDao.getAllRole();
    }

    @Override
    public void addPermissionForRole(Integer roleId, List<Integer> permissionList) {

    }


    /**
     * 删除角色拥有的全部权限
     * @param roleId
     * @return
     */
    @Override
    public boolean deleteAllPermissionByRoleId(Integer roleId) {
        List<Integer> permissionByRoleId = permissionDao.getPermissionByRoleId(roleId);
        if(permissionByRoleId == null || permissionByRoleId.size() == 0){
            return true;
        }
        return roleDao.deleteAllPermissionByRoleId(roleId);
    }

    /**
     * 批量为角色添加权限
     * @param roleId
     * @param permissionList
     * @return
     */
    @Override
    public boolean batchAddPermissionForRole(Integer roleId, List<Integer> permissionList) {
        if(permissionList.size() == 0){
            return true;
        }
        return roleDao.batchAddPermissionForRole(roleId,permissionList);
    }

    @Override
    public boolean addRole(Role role) {
        return roleDao.addRole(role);
    }

    @Override
    public boolean updateRole(Role role) {
        return roleDao.updateRole(role);
    }

    @Override
    public boolean addRoleForUserOrAdmin(Integer userId, Integer roleId) {
        return roleDao.addRoleForUserOrAdmin(userId,roleId);
    }

    @Override
    public boolean updateRoleForUserOrAdmin(Integer userId, Integer roleId) {
        return roleDao.updateRoleForUserOrAdmin(userId,roleId);
    }

}
