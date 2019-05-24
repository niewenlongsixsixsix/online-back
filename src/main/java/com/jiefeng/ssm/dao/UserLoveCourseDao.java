package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.bean.UserLoveCourse;
import org.apache.ibatis.annotations.Param;

public interface UserLoveCourseDao {

    boolean addUserLoveCourse(UserLoveCourse userLoveCourse);

    boolean deleteUserLoveCourse(@Param("userId") Integer userId,@Param("courseId") Integer courseId);

    UserLoveCourse getUserLoveCourse(@Param("userId") Integer userId,@Param("courseId") Integer courseId);
}
