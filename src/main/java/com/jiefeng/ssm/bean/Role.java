package com.jiefeng.ssm.bean;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {

  private Integer id;
  private String roleName;
  private String roleDesc;
  private Date createTime;
  private String functionDesc;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }


  public String getRoleDesc() {
    return roleDesc;
  }

  public void setRoleDesc(String roleDesc) {
    this.roleDesc = roleDesc;
  }

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public String getFunctionDesc() {
    return functionDesc;
  }

  public void setFunctionDesc(String functionDesc) {
    this.functionDesc = functionDesc;
  }


  public Role(Integer id) {
    this.id = id;
  }

  public Role() {
  }

  @Override
  public String toString() {
    return "Role{" +
            "id=" + id +
            ", roleName='" + roleName + '\'' +
            ", roleDesc='" + roleDesc + '\'' +
            ", createTime=" + createTime +
            ", functionDesc='" + functionDesc + '\'' +
            '}';
  }
}
