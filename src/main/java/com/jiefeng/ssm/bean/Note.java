package com.jiefeng.ssm.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable {

  private Integer id;
  private String title;
  private String content;
  private User createBy;
  private Date createTime;
  private Integer priority;
  private Integer highQuality;
  private Integer publicVisible;
  private Date updateTime;
  private String imgUrl;


  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
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


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public User getCreateBy() {
    return createBy;
  }

  public void setCreateBy(User createBy) {
    this.createBy = createBy;
  }
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public long getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }


  public long getHighQuality() {
    return highQuality;
  }

  public void setHighQuality(Integer highQuality) {
    this.highQuality = highQuality;
  }


  public long getPublicVisible() {
    return publicVisible;
  }

  public void setPublicVisible(Integer publicVisible) {
    this.publicVisible = publicVisible;
  }

  @Override
  public String toString() {
    return "Note{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", createBy=" + createBy +
            ", createTime=" + createTime +
            ", priority=" + priority +
            ", highQuality=" + highQuality +
            ", publicVisible=" + publicVisible +
            ", updateTime=" + updateTime +
            ", imgUrl='" + imgUrl + '\'' +
            '}';
  }
}
