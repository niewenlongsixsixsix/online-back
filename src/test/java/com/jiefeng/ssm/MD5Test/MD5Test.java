package com.jiefeng.ssm.MD5Test;

import com.jiefeng.ssm.BaseTest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

public class MD5Test extends BaseTest {

    @Test
    public void testMD5(){
        String password =  "666";
        //对密码进行MD5加密
        //fae0b27c451c728867a567e8c1bb4e53
        Md5Hash md5Hash = new Md5Hash(password);
        System.out.println(md5Hash);

        //进行加盐操作
        //2f1f526e25fdefa341c7a302b47dd9df
        Md5Hash md5Hash1 = new Md5Hash(password,"zhangsan");
        System.out.println(md5Hash1);

        //增加迭代次数，循环加密
        //cd757bae8bd31da92c6b14c235668091
        Md5Hash md5Hash2 = new Md5Hash(password,"zhangsan",3);
        System.out.println(md5Hash2);
    }
}
