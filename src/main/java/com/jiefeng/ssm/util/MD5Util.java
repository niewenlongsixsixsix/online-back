package com.jiefeng.ssm.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class MD5Util {

    /**
     * 对密码加盐进行MD5加密，并且将加密后的字符串返回出去
     * @param encryptionTarget 要加密的对象
     * @param salt 加盐
     * @return
     */
    public static String Md5Encryption(String encryptionTarget, String salt){

        if(encryptionTarget == null){
            System.out.println("en null");
        }
        if(salt == null){
            System.out.println("salt null");
        }
        Md5Hash md5Hash = new Md5Hash(encryptionTarget,salt,1);
        return md5Hash.toString();

    }

}
