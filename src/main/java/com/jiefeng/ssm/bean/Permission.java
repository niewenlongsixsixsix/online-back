package com.jiefeng.ssm.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Permission implements Serializable {

  private Integer id;
  private String permissionName;
  private String permissionDesc;
  private Date createTime;

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  public Date getCreateTime() {
    return createTime;
  }

}
