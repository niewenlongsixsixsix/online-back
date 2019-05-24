package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.bean.Exception;

import java.util.List;

public interface ExceptionDao {

    /**
     * 添加异常
     * @param e
     * @return
     */
    boolean addException(Exception e);

    List<Exception> getAllException();
}
