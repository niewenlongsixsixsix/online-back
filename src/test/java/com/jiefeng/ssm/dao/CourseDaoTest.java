package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.BaseTest;
import com.jiefeng.ssm.bean.Classification;
import com.jiefeng.ssm.bean.Course;
import com.jiefeng.ssm.bean.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class CourseDaoTest extends BaseTest {

    @Autowired
    private CourseDao courseDao;

    @Test
    public void testGetAllCourseByUserId(){

        List<Course> allCourseByUserId = courseDao.getAllCourseByUserId(1540735);
        System.out.println(allCourseByUserId);
    }

    @Test
    public void testAddCourse(){
        Course course = new Course();
        course.setTitle("SpringCloud分布式配置中心");
        course.setCreateBy(new User(1540735));
        course.setBelongTo(new Classification(12415));
        course.setPriority(0);
        course.setImgUrl("http://static.itmayiedu.com/0000000000010.jpg");
        course.setCreateTime(new Date());

        courseDao.addCourse(course);
    }

    @Test
    public void testUpdateCourse(){
        Course course = new Course();
        course.setId(2);
        course.setTitle("22222");
        courseDao.updateCourse(course);
    }

    @Test
    public void testGetCourseByPrimaryKey(){
        Course courseByPrimaryKey = courseDao.getCourseByPrimaryKey(2);
        System.out.println(courseByPrimaryKey);
    }
}
