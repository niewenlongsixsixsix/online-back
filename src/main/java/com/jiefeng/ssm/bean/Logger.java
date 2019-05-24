package com.jiefeng.ssm.bean;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Logger implements Serializable {

  private Integer id;
  private String username;
  private String ip;
  private String method;
  private Long spendTime;
  private Date createTime;
  private String loggerDesc;
  private String methodArgs;


  public String getMethodArgs() {
    return methodArgs;
  }

  public void setMethodArgs(String methodArgs) {
    this.methodArgs = methodArgs;
  }

  public String getLoggerDesc() {
    return loggerDesc;
  }

  public void setLoggerDesc(String loggerDesc) {
    this.loggerDesc = loggerDesc;
  }

  public long getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }


  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }


  public long getSpendTime() {
    return spendTime;
  }

  public void setSpendTime(Long spendTime) {
    this.spendTime = spendTime;
  }

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

}
