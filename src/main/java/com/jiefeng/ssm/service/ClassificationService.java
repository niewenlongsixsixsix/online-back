package com.jiefeng.ssm.service;

import com.jiefeng.ssm.bean.Classification;

import java.util.List;

public interface ClassificationService {

    /**
     * 获取所有的分类
     * @return
     */
    List<Classification> getAllClassification();

}
