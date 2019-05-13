package com.jiefeng.ssm.beanExtend;

import java.io.Serializable;

public class UserExtend implements Serializable {
    private int id;
    private String username;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
