package com.jiefeng.ssm.service.impl;

import com.jiefeng.ssm.bean.Logger;
import com.jiefeng.ssm.dao.LoggerDao;
import com.jiefeng.ssm.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("LoggerService")
public class LoggerServiceImpl implements LoggerService {

    @Autowired
    private LoggerDao loggerDao;

    @Override
    public List<Logger> getAllLogger() {
        return loggerDao.getAllLogger();
    }


}
