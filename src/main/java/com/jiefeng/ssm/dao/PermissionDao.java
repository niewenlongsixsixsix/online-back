package com.jiefeng.ssm.dao;

import java.util.List;

public interface PermissionDao {
    /**
     * 获取用户所有的权限
     * @param userId
     * @return
     */
    List<String> getUserAllPermission(long userId);
}
