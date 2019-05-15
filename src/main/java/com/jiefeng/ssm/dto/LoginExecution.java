package com.jiefeng.ssm.dto;

import com.jiefeng.ssm.enums.LoginStateEnums;

public class LoginExecution {

    private int state;
    private String stateInfo;

    public LoginExecution(LoginStateEnums stateEnums) {
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
