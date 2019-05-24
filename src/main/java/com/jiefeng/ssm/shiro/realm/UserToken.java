package com.jiefeng.ssm.shiro.realm;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UserToken extends UsernamePasswordToken {

    private Integer type;

    public UserToken(){}

    public UserToken(final String username,final String password, final Integer type){
        super(username,password);
        this.type = type;
    }

    public Integer getLoginType() {
        return type;
    }

    public void setLoginType(Integer loginType) {
        this.type = loginType;
    }

}
