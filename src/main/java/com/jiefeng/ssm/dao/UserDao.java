package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.bean.User;

import java.util.List;

public interface UserDao {

    User getUsernameByUsername(String username);

    List<String> getUserPermission(String username);

    List<String> getUserRole(String username);
}
