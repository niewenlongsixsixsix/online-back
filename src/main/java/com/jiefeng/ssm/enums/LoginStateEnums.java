package com.jiefeng.ssm.enums;

public enum LoginStateEnums  {

    SUCCESS(110,"登录成功"), EMPTY(-98,"没有此用户"), PASSWORD_ERROR(-1,"密码错误"),
    SYSTEM_ERROR(-230,"未知错误");

    private int state;
    private String stateInfo;

    LoginStateEnums(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }


    public static LoginStateEnums stateOf(int state){
        for (LoginStateEnums enums : values()) {
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
