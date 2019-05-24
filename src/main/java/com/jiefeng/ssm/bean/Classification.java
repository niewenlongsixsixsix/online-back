package com.jiefeng.ssm.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Classification implements Serializable {

  private Integer id;
  private String name;
  private Date createTime;
  private Integer status;


  public Classification(Integer id) {
    this.id = id;
  }

  public Classification() {
  }
}
