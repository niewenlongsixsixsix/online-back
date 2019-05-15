package com.jiefeng.ssm.web.common;

import com.jiefeng.ssm.bean.Course;
import com.jiefeng.ssm.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/course")
@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     *
     * @param type
     * @param id
     * @return
     */
    @RequestMapping(value = "/getAllCourseByUserId/{type}/{id}",method = RequestMethod.GET)
    @ResponseBody
    public List<Course> getAllCourseByUserId(@PathVariable int type, @PathVariable long id){

        List<Course> allCourseListByUserId;
        // 0 普通用户 1 管理员查询
        if(type == 0){
            allCourseListByUserId = courseService.getAllCourseListByUserId();
        }else{
            allCourseListByUserId = courseService.getAllCourseListByUserId(id);
        }

        return allCourseListByUserId;
    }
}
