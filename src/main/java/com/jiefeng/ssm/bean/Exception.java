package com.jiefeng.ssm.bean;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Exception implements Serializable {

  private Long id;
  private String username;
  private String ip;
  private String exceptionDesc;
  private String method;
  private Date createTime;
  private String exceptionDetail;
  private String methodArgs;

  public String getMethodArgs() {
    return methodArgs;
  }

  public void setMethodArgs(String methodArgs) {
    this.methodArgs = methodArgs;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
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


  public String getExceptionDesc() {
    return exceptionDesc;
  }

  public void setExceptionDesc(String exceptionDesc) {
    this.exceptionDesc = exceptionDesc;
  }


  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }


  public String getExceptionDetail() {
    return exceptionDetail;
  }

  public void setExceptionDetail(String exceptionDetail) {
    this.exceptionDetail = exceptionDetail;
  }

}
