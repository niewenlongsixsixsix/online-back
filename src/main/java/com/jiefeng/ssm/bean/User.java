package com.jiefeng.ssm.bean;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {

  private Integer id;
  private String username;
  private String password;
  private String phone;
  private String email;
  private String address;
  private Date registerTime;
  private Date lastLoginTime;
  private Integer status;
  private String salt;
  private User createBy;
  private User updateBy;
  private Date updateTime;
  private String avatar;
  private Integer type;
  private Integer age;
  private Integer gender;

  public User(String email, String password) {
    this.username = email;
    this.password = password;
  }

  public User(Integer id) {
    this.id = id;
  }

  public User() {
  }

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  public Date getRegisterTime() {
    return registerTime;
  }
}
