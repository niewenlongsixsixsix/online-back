package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.bean.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleDao {

    /**
     * 获取用户拥有的角色
     * @param userId
     * @return
     */
    List<String> getUserAllRole(Integer userId);

    /**
     * 根据主键获取角色
     * @param roleId
     * @return
     */
    Role getRoleByPrimaryKey(Integer roleId);

    /**
     * 获取所有的角色
     * @return
     */
    List<Role> getAllRole();

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
    boolean batchAddPermissionForRole(@Param("roleId") long roleId, @Param("permissionList") List<Integer> permissionList);

    /**
     * 添加角色
     * @param role
     * @return
     */
    boolean addRole(Role role);

    /**
     * 更新角色
     * @param role
     * @return
     */
    boolean updateRole(Role role);

    /**
     * 为一个用户添加一种角色身份
     * @param userId
     * @param roleId
     * @return
     */
    boolean addRoleForUserOrAdmin(@Param("userId") long userId,@Param("roleId") long roleId);

    boolean updateRoleForUserOrAdmin(@Param("userId") long userId,@Param("roleId") long roleId);
}
