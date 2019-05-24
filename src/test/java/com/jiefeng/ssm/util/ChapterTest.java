package com.jiefeng.ssm.util;

import com.jiefeng.ssm.BaseTest;
import com.jiefeng.ssm.beanExtend.BigChapterExtend;
import com.jiefeng.ssm.beanExtend.CourseChapterExtend;
import com.jiefeng.ssm.service.BigChapterService;
import com.jiefeng.ssm.util.beanExtendUtil.BigChapterUtil;
import com.jiefeng.ssm.util.beanExtendUtil.ChapterUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ChapterTest extends BaseTest {

    @Autowired
    private BigChapterService bigChapterService;

    @Test
    public void testChapter(){
        List<BigChapterExtend> bigChapterByCourseId = bigChapterService.getBigChapterByCourseId(1);

        List<CourseChapterExtend> allChapter = new ChapterUtil().getAllChapter(bigChapterByCourseId);
        System.out.println(allChapter);
    }
}
