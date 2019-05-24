package com.jiefeng.ssm.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BigChapter implements Serializable {

  private Integer id;
  private String title;
  private String chapterDescribe;
  private Course courseId;
  private Date createTime;
  private Integer status;

}
