package com.jiefeng.ssm.bean;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Course implements Serializable {

  private Integer id;
  private String title;
  private String subTitle;
  private User createBy;
  private Date createTime;
  private String imgUrl;
  private Integer priority;
  private Classification belongTo;
  private Integer status;


  public Course(Integer id, String imgUrl) {
    this.id = id;
    this.imgUrl = imgUrl;
  }

  public Course(Integer id) {
    this.id = id;
  }

  public Course() {
  }
}
