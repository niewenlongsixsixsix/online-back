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

    /**
     * 获取所有的分类
     * @return
     */
    @Override
    public List<Classification> getAllClassification() {

        List<Classification> allClassification = classificationDao.getAllClassification();

        return allClassification;
    }

    /**
     * 添加分类
     * @param classification
     * @return
     */
    @Override
    public boolean addClassification(Classification classification) {
        return classificationDao.addClassification(classification);
    }

    @Override
    public boolean updateClassification(Classification classification) {
        return classificationDao.updateClassification(classification);
    }

    @Override
    public Classification getClassificationByPrimaryKey(Integer id) {
        return classificationDao.getClassificationByPrimaryKey(id);
    }
}
