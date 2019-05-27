package com.jiefeng.ssm.bean;


import lombok.Data;

import java.util.Date;


public class Headlines {

  private Integer id;
  private String imgUrl;
  private Integer createForCourse;
  private Integer priority;
  private Integer enableStatus;
  private Date createTime;
  private Integer createBy;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  public Integer getCreateForCourse() {
    return createForCourse;
  }

  public void setCreateForCourse(Integer createForCourse) {
    this.createForCourse = createForCourse;
  }

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public Integer getEnableStatus() {
    return enableStatus;
  }

  public void setEnableStatus(Integer enableStatus) {
    this.enableStatus = enableStatus;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Integer getCreateBy() {
    return createBy;
  }

  public void setCreateBy(Integer createBy) {
    this.createBy = createBy;
  }

  @Override
  public String toString() {
    return "Headlines{" +
            "id=" + id +
            ", imgUrl='" + imgUrl + '\'' +
            ", createForCourse=" + createForCourse +
            ", priority=" + priority +
            ", createTime=" + createTime +
            '}';
  }
}
