package com.jiefeng.ssm.bean;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PermissionCategory implements Serializable {

  private Integer id;
  private String permissionName;
  private String permissionDesc;
  private Date createTime;
  private List<Permission> children;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getPermissionName() {
    return permissionName;
  }

  public void setPermissionName(String permissionName) {
    this.permissionName = permissionName;
  }

  public String getPermissionDesc() {
    return permissionDesc;
  }

  public void setPermissionDesc(String permissionDesc) {
    this.permissionDesc = permissionDesc;
  }

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public List<Permission> getChildren() {
    return children;
  }

  public void setChildren(List<Permission> children) {
    this.children = children;
  }

  @Override
  public String toString() {
    return "PermissionCategory{" +
            "id=" + id +
            ", permissionName='" + permissionName + '\'' +
            ", permissionDesc='" + permissionDesc + '\'' +
            ", createTime=" + createTime +
            ", children=" + children +
            '}';
  }
}
