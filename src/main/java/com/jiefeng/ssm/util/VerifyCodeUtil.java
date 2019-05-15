package com.jiefeng.ssm.util;

public class VerifyCodeUtil {

    /**
     * 生成验证码
     * @return
     */
    public static int getVerifyCode(){
        return (int)((Math.random()*9+1)*100000);
    }

    public static void main(String[] args) {
        System.out.println(VerifyCodeUtil.getVerifyCode());
    }
}
