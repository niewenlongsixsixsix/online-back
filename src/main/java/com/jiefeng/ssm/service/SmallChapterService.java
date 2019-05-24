package com.jiefeng.ssm.service;

import com.jiefeng.ssm.bean.SmallChapter;

import java.util.List;
import java.util.Map;

public interface SmallChapterService {

    /**
     * 根据大章节ID获取课程
     * @param bigChapterId
     * @return
     */
    List<SmallChapter> getAllSmallChapter(Integer bigChapterId);

    boolean addSmallChapter(SmallChapter smallChapter);

    boolean updateSmallChapter(SmallChapter smallChapter);


    SmallChapter getSmallChapterByPrimaryKey(Integer smallChapterId);


    /**
     * 课程详情页面需要加载目录
     * @param courseId
     * @return
     */
    Map<String,Object> getAllChapter(Integer courseId);

}
