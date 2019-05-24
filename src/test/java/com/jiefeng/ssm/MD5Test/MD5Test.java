package com.jiefeng.ssm.MD5Test;

import com.jiefeng.ssm.BaseTest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

public class MD5Test extends BaseTest {

    @Test
    public void testMD5(){
        String password =  "nie16226";
        String username = "123456";

        //增加迭代次数，循环加密
        //cd757bae8bd31da92c6b14c235668091
        Md5Hash md5Hash2 = new Md5Hash(password,username,1);
        System.out.println(md5Hash2);
    }

}
