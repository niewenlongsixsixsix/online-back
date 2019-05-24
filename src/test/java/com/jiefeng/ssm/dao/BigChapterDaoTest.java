package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.BaseTest;
import com.jiefeng.ssm.bean.BigChapter;
import com.jiefeng.ssm.bean.Course;
import com.jiefeng.ssm.beanExtend.BigChapterExtend;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BigChapterDaoTest extends BaseTest {

    @Autowired
    private BigChapterDao bigChapterDao;

    @Test
    public void testGetBigChapterByCourseId(){

        List<BigChapterExtend> list = new ArrayList<>();

        List<BigChapter> allBigChapterByCourseId = bigChapterDao.getAllBigChapterByCourseId(1);
        for (BigChapter bigChapter : allBigChapterByCourseId) {
            BigChapterExtend bigChapterExtend = new BigChapterExtend();
            bigChapterExtend.setId(bigChapter.getId());
            bigChapterExtend.setTitle(bigChapter.getTitle());
            bigChapterExtend.setDescribe(bigChapter.getChapterDescribe());
            bigChapterExtend.setCreateTime(bigChapter.getCreateTime());
            list.add(bigChapterExtend);
        }

        for (BigChapterExtend bigChapterExtend : list) {

            System.out.println(bigChapterExtend);
        }
    }

    @Test
    public void testAddBigChapter(){
        BigChapter chapter = new BigChapter();
        chapter.setTitle("第2章 kubernetes快速入门【k8s必知必会】");
        chapter.setChapterDescribe("本章中将从核心概念、架构设计、认证授权");
        chapter.setCreateTime(new Date());
        chapter.setCourseId(new Course(1));
        boolean b = bigChapterDao.addBigChapter(chapter);
        Assert.assertEquals(true,b);

    }


    @Test
    public void testUpdateBigChapterByPrimaryKey(){
        BigChapter bigChapter = new BigChapter();

        bigChapter.setTitle("MyCat分片集群分表分库策略");
        bigChapter.setChapterDescribe("课程有效期： 永久有效");
        bigChapter.setId(2);

        boolean b = bigChapterDao.updateBigChapter(bigChapter);

        Assert.assertEquals(true,b);
    }
}
