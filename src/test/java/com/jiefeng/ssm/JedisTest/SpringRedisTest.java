package com.jiefeng.ssm.JedisTest;

import com.jiefeng.ssm.BaseTest;
import com.jiefeng.ssm.enums.LoginStateEnums;
import com.jiefeng.ssm.redis.JedisUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SpringRedisTest extends BaseTest {

    @Autowired
    private JedisUtil.Strings strings;

    @Test
    public void testRedisKeys(){

        System.out.println(LoginStateEnums.SUCCESS.getStateInfo());
    }
}
