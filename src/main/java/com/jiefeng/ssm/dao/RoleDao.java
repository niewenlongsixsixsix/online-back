package com.jiefeng.ssm.dao;

import java.util.List;

public interface RoleDao {

    /**
     * 获取用户拥有的角色
     * @param userId
     * @return
     */
    List<String> getUserAllRole(long userId);

}
