package com.jiefeng.ssm.bean;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

public class SmallChapter implements Serializable {

  private Integer id;
  private String title;
  private String videoUrl;
  private Date createTime;
  private Integer bigChapterId;
  private Integer status;


  public SmallChapter(Integer id, String videoUrl) {
    this.id = id;
    this.videoUrl = videoUrl;
  }

  public SmallChapter() {
  }

  public long getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getVideoUrl() {
    return videoUrl;
  }

  public void setVideoUrl(String videoUrl) {
    this.videoUrl = videoUrl;
  }

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Integer getBigChapterId() {
    return bigChapterId;
  }

  public void setBigChapterId(Integer bigChapterId) {
    this.bigChapterId = bigChapterId;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "SmallChapter{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", videoUrl='" + videoUrl + '\'' +
            ", createTime=" + createTime +
            ", bigChapterId=" + bigChapterId +
            ", status=" + status +
            '}';
  }
}
