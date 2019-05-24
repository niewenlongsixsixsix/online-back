package com.jiefeng.ssm.beanExtend;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserExtend implements Serializable {
    private Integer id;
    private String username;
    private Integer type;
    private String avatar;
}
