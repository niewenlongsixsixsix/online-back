package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.BaseTest;
import com.jiefeng.ssm.bean.SmallChapter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class SmallChapterDaoTest extends BaseTest {

    @Autowired
    private SmallChapterDao smallChapterDao;


    @Test
    public void testGetAllSmallChapter(){
        List<SmallChapter> allSmallChapterByBigChapterId = smallChapterDao.getAllSmallChapterByBigChapterId(1);
        System.out.println(allSmallChapterByBigChapterId);
    }

    @Test
    public void testAddSmallChapter(){
        SmallChapter smallChapter = new SmallChapter();

        smallChapter.setTitle("dfsjkjfsd");
        smallChapter.setBigChapterId(1);
        smallChapter.setCreateTime(new Date());

        boolean b = smallChapterDao.addSmallChapter(smallChapter);
        System.out.println(b);
    }

    @Test
    public void testUpdateSmallChapter(){
        SmallChapter smallChapter = new SmallChapter();
        smallChapter.setVideoUrl("http://www.baidu.com");
        smallChapter.setId(2);
        boolean b = smallChapterDao.updateSmallChapter(smallChapter);
        System.out.println(b);
    }
}
