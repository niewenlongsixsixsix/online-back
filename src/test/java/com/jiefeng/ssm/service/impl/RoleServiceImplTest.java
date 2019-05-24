package com.jiefeng.ssm.service.impl; 

import com.jiefeng.ssm.BaseTest;
import com.jiefeng.ssm.bean.Role;
import com.jiefeng.ssm.service.RoleService;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/** 
* RoleServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>$tody</pre> 
* @version 1.0 
*/ 
public class RoleServiceImplTest extends BaseTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void testGetAllRole() throws Exception {
        List<Role> allRole = roleService.getAllRole();
        System.out.println(allRole);
    }


} 
