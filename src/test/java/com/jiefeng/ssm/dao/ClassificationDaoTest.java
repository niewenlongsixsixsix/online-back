package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.BaseTest;
import com.jiefeng.ssm.bean.Classification;
import org.junit.Assert;
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


    @Test
    public void addClassification(){
        Classification classification = new Classification();
        classification.setName("操作系统");
        boolean b = classificationDao.addClassification(classification);

        Assert.assertEquals(true,b);
    }

    @Test
    public void updateClassification(){
        Classification classification = new Classification();
        classification.setId(12410);
        classification.setStatus(0);

        boolean b = classificationDao.updateClassification(classification);
        System.out.println(b);
    }

}
