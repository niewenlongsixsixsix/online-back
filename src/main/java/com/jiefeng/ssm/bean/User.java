package com.jiefeng.ssm.bean;


import lombok.Data;

import java.util.Date;

@Data
public class User {

  private int id;
  private String username;
  private String password;
  private String phone;
  private String email;
  private String address;
  private Date registerTime;
  private Date lastLoginTime;
  private int status;
  private String salt;
  private String createBy;
  private String updateBy;
  private Date updateTime;
  private String avatar;
  private int type;
  private int age;
  private int gender;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public User() {
  }
}
