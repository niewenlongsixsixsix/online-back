package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.bean.BigChapter;

import java.util.List;

public interface BigChapterDao {


    /**
     * 添加章节
     * @param bigChapter
     * @return
     */
    boolean addBigChapter(BigChapter bigChapter);

    /**
     * 查找课程的所有章节
     * @param courseId
     * @return
     */
    List<BigChapter> getAllBigChapterByCourseId(Integer courseId);


    boolean updateBigChapter(BigChapter bigChapter);

    BigChapter getBigChapterByPrimaryKey(Integer id);
}
