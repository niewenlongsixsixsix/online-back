package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.bean.Course;

import java.util.List;

public interface CourseDao {

    /**
     * 根据用户ID获取用户的课程
     * @param UserId
     * @return
     */
    List<Course>  getAllCourseByUserId(long UserId);

}
