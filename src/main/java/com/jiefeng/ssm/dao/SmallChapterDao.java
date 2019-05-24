package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.bean.SmallChapter;

import java.util.List;

public interface SmallChapterDao {

    List<SmallChapter> getAllSmallChapterByBigChapterId(Integer BigChapterId);

    boolean addSmallChapter(SmallChapter smallChapter);

    boolean updateSmallChapter(SmallChapter smallChapter);

    SmallChapter getSmallChapterByPrimaryKey(Integer smallChapterId);

}
