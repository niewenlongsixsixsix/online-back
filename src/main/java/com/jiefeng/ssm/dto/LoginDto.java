package com.jiefeng.ssm.dto;

import com.jiefeng.ssm.enums.LoginStateEnums;

public class LoginDto {

    private int state;
    private String stateInfo;

    public LoginDto(LoginStateEnums stateEnums) {
        this.state = stateEnums.getState();
        this.stateInfo = stateEnums.getStateInfo();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }
}
