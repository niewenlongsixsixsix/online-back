package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.bean.Classification;

import java.util.List;


public interface ClassificationDao {

    /**
     * 添加大分类
     * @return
     */
    boolean addClassification(Classification classification);


    /**
     * 获取所有的分类
     * @return
     */
    List<Classification> getAllClassification();

    /**
     * 根据ID获取分类
     * @param id
     * @return
     */
    Classification getClassificationByPrimaryKey(long id);


}
