package com.jiefeng.ssm.enums;

public enum UserStateEnums {

    SUCCESS(110,"登录成功"), SYSTEM_ERROR(-230,"未知错误");

    private int state;
    private String stateInfo;

    UserStateEnums(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }


    public static UserStateEnums stateOf(int state){
        for (UserStateEnums enums : values()) {
            if(enums.getState() == state){
                return enums;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
