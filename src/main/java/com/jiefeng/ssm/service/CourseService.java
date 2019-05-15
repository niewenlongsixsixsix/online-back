package com.jiefeng.ssm.service;

import com.jiefeng.ssm.bean.Course;

import java.util.List;

public interface CourseService {

    /**
     * 前端用户取用自己的课程列表
     * @return
     */
    List<Course> getAllCourseListByUserId();


    List<Course> getAllCourseListByUserId(long userId);


}
