package com.jiefeng.ssm.service.impl;

import com.jiefeng.ssm.bean.UserLoveCourse;
import com.jiefeng.ssm.dao.UserLoveCourseDao;
import com.jiefeng.ssm.service.UserLoveCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserLoveCourseService")
public class UserLoveCourseServiceImpl implements UserLoveCourseService {

    @Autowired
    private UserLoveCourseDao userLoveCourseDao;

    @Override
    public boolean addUserLoveCourse(UserLoveCourse userLoveCourse) {
        return userLoveCourseDao.addUserLoveCourse(userLoveCourse);
    }

    @Override
    public boolean deleteUserLoveCourse(Integer userId,Integer courseId) {
        return userLoveCourseDao.deleteUserLoveCourse(userId,courseId);
    }

    @Override
    public boolean getUserLoveCourse(Integer userId, Integer courseId) {
        UserLoveCourse userLoveCourse = userLoveCourseDao.getUserLoveCourse(userId, courseId);

        if(userLoveCourse == null){
            return false;
        }
        return true;
    }
}
