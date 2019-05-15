package com.jiefeng.ssm.enums;

public enum RegisterStateEnums {

    SUCCESS(200,"成功"),
    VERIFY_CODE_ERROR(-100,"错误的验证码"),
    VERIFY_CODE_INVALID(-102,"验证码已经失效"),
    EMPTY(-103,"验证码为空"),
    ACCOUNT_EXIST(-310,"帐号已经注册"),
    SYSTEM_ERROR(-500,"未知错误");

    private int state;
    private String stateInfo;

    RegisterStateEnums(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }


    public static RegisterStateEnums stateOf(int state){
        for (RegisterStateEnums enums : values()) {
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
