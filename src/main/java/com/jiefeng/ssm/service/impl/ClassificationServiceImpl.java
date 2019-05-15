package com.jiefeng.ssm.service.impl;

import com.jiefeng.ssm.bean.Classification;
import com.jiefeng.ssm.dao.ClassificationDao;
import com.jiefeng.ssm.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ClassificationService")
public class ClassificationServiceImpl implements ClassificationService {

    @Autowired
    private ClassificationDao classificationDao;

    @Override
    public List<Classification> getAllClassification() {

        List<Classification> allClassification = classificationDao.getAllClassification();

        return allClassification;
    }
}
