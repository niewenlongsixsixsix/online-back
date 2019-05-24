package com.jiefeng.ssm.service.impl; 

import com.jiefeng.ssm.BaseTest;
import com.jiefeng.ssm.beanExtend.BigChapterExtend;
import com.jiefeng.ssm.service.BigChapterService;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/** 
* BigChapterServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>$tody</pre> 
* @version 1.0 
*/ 
public class BigChapterServiceImplTest extends BaseTest {

    @Autowired
    private BigChapterService bigChapterService;

    @Test
    public void testGetBigChapterByCourseId() throws Exception {
        List<BigChapterExtend> bigChapterByCourseId = bigChapterService.getBigChapterByCourseId(1);
        System.out.println(bigChapterByCourseId);
    }


} 
