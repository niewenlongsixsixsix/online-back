package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.BaseTest;
import com.jiefeng.ssm.bean.Course;
import com.jiefeng.ssm.beanExtend.UserExtend;
import org.apache.shiro.SecurityUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CourseDaoTest extends BaseTest {

    @Autowired
    private CourseDao courseDao;

    @Test
    public void testGetAllCourseByUserId(){

        List<Course> allCourseByUserId = courseDao.getAllCourseByUserId(1540735);
        System.out.println(allCourseByUserId);
    }
}
