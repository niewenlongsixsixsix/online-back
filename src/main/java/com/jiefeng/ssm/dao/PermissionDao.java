package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.bean.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionDao {
    /**
     * 获取用户所有的权限
     *
     * @param userId
     * @return
     */
    List<String> getUserAllPermission(Integer userId);


    List<Permission> getPerPermissionCategoryPermission(Integer permissionCategoryId);

    /**
     * @param roleId
     * @return
     */
    List<Integer> getPermissionByRoleId(Integer roleId);

    List<Integer> getPermissionByAdminId(Integer adminId);


}