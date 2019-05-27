package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.BaseTest;
import com.jiefeng.ssm.bean.Headlines;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class HeadLineDaoTest extends BaseTest {

    @Autowired
    private HeadlinesDao headlinesDao;

    @Test
    public void testAddHeadLine(){
        Headlines headlines = new Headlines();
        headlines.setImgUrl("/upload");
        headlines.setCreateForCourse(1);
        headlines.setCreateTime(new Date());
        boolean b = headlinesDao.addHeadLine(headlines);
        System.out.println(b);
    }

    @Test
    public void testUpdateHeadLine(){
        Headlines headlines = new Headlines();
        headlines.setImgUrl("/dfjdksfj");
        headlines.setId(3);
        System.out.println(headlines);
        headlinesDao.updateHeadLine(headlines);
    }
}
