package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.bean.Course;

import java.util.List;

public interface CourseDao {

    /**
     * 根据用户ID获取用户的课程
     * @param UserId
     * @return
     */
    List<Course>  getAllCourseByUserId(Integer UserId);

    List<Course> getAllCourse();

    List<Course> getAllCourseByClassification(int classification);

    Course getCourseByPrimaryKey(Integer id);
    /**
     * 添加课程
     * @param course
     * @return
     */
    boolean addCourse(Course course);


    boolean updateCourse(Course course);

    /**
     * 删除课程
     * @param courseId
     * @return
     */
    boolean delete(Integer courseId);

    List<Course> getAllCourseByLoveCourseId(Integer userId);

    /**
     * 0 最新课程  1 最热课程
     * @param type
     * @return
     */
    List<Course> hotOrNewCourse(Integer type);
}
