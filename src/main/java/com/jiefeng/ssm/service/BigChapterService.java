package com.jiefeng.ssm.service;

import com.jiefeng.ssm.bean.BigChapter;
import com.jiefeng.ssm.beanExtend.BigChapterExtend;

import java.util.List;

public interface BigChapterService {

    /**
     * 根据课程ID获取其章节信息
     * @param courseId
     * @return
     */
    List<BigChapterExtend> getBigChapterByCourseId(Integer courseId);

    /**
     * 添加章节信息
     * @param bigChapter
     * @return
     */
    boolean addBigChapter(BigChapter bigChapter);

    /**
     * 更新章节信息
     * @param bigChapter
     * @return
     */
    boolean updateChapter(BigChapter bigChapter);

    /**
     * 根据id获取章节
     * @param id
     * @return
     */
    BigChapter getBigChapterByPrimaryKey(Integer id);
}
