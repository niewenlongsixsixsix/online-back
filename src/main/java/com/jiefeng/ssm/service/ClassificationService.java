package com.jiefeng.ssm.service;

import com.jiefeng.ssm.bean.Classification;

import java.util.List;

public interface ClassificationService {

    /**
     * 获取所有的分类
     * @return
     */
    List<Classification> getAllClassification();

    /**
     * 添加分类
     * @param classification
     * @return
     */
    boolean addClassification(Classification classification);


    /**
     * 更新分类信息
     * @param classification
     * @return
     */
    boolean updateClassification(Classification classification);

    /**
     * 根据主键获取分类
     * @param id
     * @return
     */
    Classification getClassificationByPrimaryKey(Integer id);

}
