package com.jiefeng.ssm.service;

import com.jiefeng.ssm.bean.PermissionCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionService {


    List<PermissionCategory> getAllPermissionByCategory();

    List<Integer> getPermissionByRoleId(Integer roleId);

    List<Integer> getPermissionByAdminId(Integer adminId);


}
