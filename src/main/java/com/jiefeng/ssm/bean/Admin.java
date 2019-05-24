package com.jiefeng.ssm.bean;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Admin implements Serializable {

  private Integer id;
  private String authAccount;
  private String password;
  private String salt;
  private Integer type;
  private Integer createBy;
  private String name;
  private Date createTime;
  private Integer status;
  private Role role;


  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getAuthAccount() {
    return authAccount;
  }

  public void setAuthAccount(String authAccount) {
    this.authAccount = authAccount;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }


  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }


  public Integer getCreateBy() {
    return createBy;
  }

  public void setCreateBy(Integer createBy) {
    this.createBy = createBy;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }


  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Admin{" +
            "id=" + id +
            ", authAccount='" + authAccount + '\'' +
            ", password='" + password + '\'' +
            ", salt='" + salt + '\'' +
            ", type=" + type +
            ", createBy=" + createBy +
            ", name='" + name + '\'' +
            ", createTime=" + createTime +
            ", status=" + status +
            '}';
  }
}
