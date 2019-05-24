package com.jiefeng.ssm.service;

import com.jiefeng.ssm.bean.Logger;

import java.util.List;

public interface LoggerService {

    /**
     * 获取所有的日志
     * @return
     */
    List<Logger> getAllLogger();
}
