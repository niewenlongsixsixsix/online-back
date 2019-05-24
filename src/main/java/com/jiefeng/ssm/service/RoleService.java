package com.jiefeng.ssm.service;

import com.jiefeng.ssm.bean.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleService {

    List<Role> getAllRole();

    void addPermissionForRole(Integer roleId,List<Integer> permissionList);


    /**
     * 删除role所有的权限
     * @param roleId
     * @return
     */
    boolean deleteAllPermissionByRoleId(Integer roleId);

    /**
     * 为角色批量添加权限
     * @param roleId
     * @param permissionList
     * @return
     */
    boolean batchAddPermissionForRole(Integer roleId,List<Integer> permissionList);


    boolean addRole(Role role);

    boolean updateRole(Role role);

    boolean addRoleForUserOrAdmin(Integer userId,Integer roleId);

    boolean updateRoleForUserOrAdmin(Integer userId,Integer roleId);
}
