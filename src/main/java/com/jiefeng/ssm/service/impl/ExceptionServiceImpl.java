package com.jiefeng.ssm.service.impl;

import com.jiefeng.ssm.bean.Exception;
import com.jiefeng.ssm.dao.ExceptionDao;
import com.jiefeng.ssm.service.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ExceptionService")
public class ExceptionServiceImpl implements ExceptionService {

    @Autowired
    private ExceptionDao exceptionDao;

    @Override
    public List<Exception> getAllException() {
        return exceptionDao.getAllException();
    }
}
