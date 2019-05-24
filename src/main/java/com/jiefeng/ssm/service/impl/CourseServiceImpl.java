package com.jiefeng.ssm.service.impl;

import com.jiefeng.ssm.bean.Admin;
import com.jiefeng.ssm.bean.BigChapter;
import com.jiefeng.ssm.bean.Course;
import com.jiefeng.ssm.beanExtend.BigChapterExtend;
import com.jiefeng.ssm.beanExtend.UserExtend;
import com.jiefeng.ssm.beanExtend.UserOrAdminType;
import com.jiefeng.ssm.dao.BigChapterDao;
import com.jiefeng.ssm.dao.CourseDao;
import com.jiefeng.ssm.service.CourseService;
import com.jiefeng.ssm.util.ImageUtil;
import com.jiefeng.ssm.util.PathUtil;
import com.jiefeng.ssm.util.beanExtendUtil.BigChapterUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("CourseService")
public class CourseServiceImpl implements CourseService {

    Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    private CourseDao courseDao;

    @Override
    public List<Course> getAllCourseListByUserId() {
        //获取当前登录的用户
        UserOrAdminType subject = (UserOrAdminType) SecurityUtils.getSubject().getPrincipal();
        UserExtend principal = (UserExtend) subject.getObject();
        logger.info(principal.toString());
        return courseDao.getAllCourseByUserId(principal.getId());
    }

    @Override
    public Course getCourseByPrimaryKey(Integer courseId) {
        return courseDao.getCourseByPrimaryKey(courseId);
    }

    @Override
    public List<Course> getAllCourseByClassification(int classificationId) {
        return courseDao.getAllCourseByClassification(classificationId);
    }

    @Override
    @Transactional
    public boolean addCourse(Course course) {
        course.setCreateTime(new Date());
        course.setImgUrl("\\course\\default.png");
        boolean b;
        try{
            b = courseDao.addCourse(course);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("addCourse 发生异常");
        }

        return b;
    }

    @Override
    @Transactional
    public boolean updateCourse(Course course) {
        boolean b;
        try{
            b = courseDao.updateCourse(course);
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException("updateCourse 发生异常");
        }
        return b;
    }

    @Override
    public List<Course> getAllCourse() {
        return courseDao.getAllCourse();
    }

    @Override
    public List<Course> getAllCourseByLoveCourseId(Integer userId) {
        return courseDao.getAllCourseByLoveCourseId(userId);
    }


}
