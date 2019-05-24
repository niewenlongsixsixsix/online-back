package com.jiefeng.ssm.beanExtend;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserOrAdminType implements Serializable {

    private int type; //0 是普通用户
    private Object object; // 1 是管理员


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
