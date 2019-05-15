package com.jiefeng.ssm.service.impl;

import com.jiefeng.ssm.bean.Course;
import com.jiefeng.ssm.beanExtend.UserExtend;
import com.jiefeng.ssm.dao.CourseDao;
import com.jiefeng.ssm.service.CourseService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CourseService")
public class CourseServiceImpl implements CourseService {

    Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    private CourseDao courseDao;

    @Override
    public List<Course> getAllCourseListByUserId() {
        //获取当前登录的用户
        Subject subject = SecurityUtils.getSubject();
        UserExtend principal = (UserExtend) subject.getPrincipal();
        logger.info("userId: " + principal.getId() + " username: " + principal.getUsername());
        return courseDao.getAllCourseByUserId(principal.getId());
    }

    @Override
    public List<Course> getAllCourseListByUserId(long userId) {
        return courseDao.getAllCourseByUserId(userId);
    }
}
