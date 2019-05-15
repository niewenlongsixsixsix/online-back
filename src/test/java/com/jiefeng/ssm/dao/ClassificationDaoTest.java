package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.BaseTest;
import com.jiefeng.ssm.bean.Classification;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClassificationDaoTest extends BaseTest {

    @Autowired
    private ClassificationDao classificationDao;

    @Test
    public void testGetAllClassification(){
        List<Classification> allClassification = classificationDao.getAllClassification();
        System.out.println(allClassification);
    }

}
