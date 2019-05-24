package com.jiefeng.ssm.service;

import com.jiefeng.ssm.bean.UserLoveCourse;

public interface UserLoveCourseService {

    boolean addUserLoveCourse(UserLoveCourse userLoveCourse);

    boolean deleteUserLoveCourse(Integer userId,Integer courseId);

    boolean getUserLoveCourse(Integer userId, Integer courseId);
}
