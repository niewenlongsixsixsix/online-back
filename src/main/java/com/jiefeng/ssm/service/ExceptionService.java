package com.jiefeng.ssm.service;

import com.jiefeng.ssm.bean.Exception;

import java.util.List;

public interface ExceptionService {

    /**
     * 获取所有的日志
     * @return
     */
    List<Exception> getAllException();


}
