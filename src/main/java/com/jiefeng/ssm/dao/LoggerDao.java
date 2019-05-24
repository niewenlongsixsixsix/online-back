package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.bean.Logger;

import java.util.List;

public interface LoggerDao {
    /**
     * 添加日志
     * @param logger
     * @return
     */
    boolean addLogger(Logger logger);

    /**
     * 获取所有的日志
     * @return
     */
    List<Logger> getAllLogger();

}
