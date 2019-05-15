package com.jiefeng.ssm.dto;

import com.jiefeng.ssm.bean.User;
import com.jiefeng.ssm.enums.UserStateEnums;

import java.util.List;

public class UserExecution {
    private int state;
    private String stateInfo;
    private User user;
    private List<User> userList;

    public UserExecution(UserStateEnums stateEnums) {
        this.state = stateEnums.getState();
        this.stateInfo = stateEnums.getStateInfo();
    }

    public UserExecution(UserStateEnums stateEnums, User user) {
        this.state = stateEnums.getState();
        this.stateInfo = stateEnums.getStateInfo();
        this.user = user;
    }

    public UserExecution(UserStateEnums stateEnums, List<User> userList) {
        this.state = stateEnums.getState();
        this.stateInfo = stateEnums.getStateInfo();
        this.userList = userList;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
