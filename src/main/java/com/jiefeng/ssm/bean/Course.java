package com.jiefeng.ssm.bean;


import lombok.Data;

import java.util.Date;

@Data
public class Course {

  private long id;
  private String title;
  private String subTitle;
  private User createBy;
  private Date createTime;
  private String imgUrl;
  private long priority;
  private Classification belongTo;

}
