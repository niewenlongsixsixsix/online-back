package com.jiefeng.ssm.web.student;

import com.jiefeng.ssm.bean.Course;
import com.jiefeng.ssm.bean.UserLoveCourse;
import com.jiefeng.ssm.beanExtend.UserExtend;
import com.jiefeng.ssm.beanExtend.UserOrAdminType;
import com.jiefeng.ssm.service.CourseService;
import com.jiefeng.ssm.service.UserLoveCourseService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("loveCourse")
public class UserLoveCourseController {

    @Autowired
    private UserLoveCourseService userLoveCourseService;

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/addLoveCourse/{courseId}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> addLoveCourse(@PathVariable Integer courseId){
        Map<String,Object> modelMap = new HashMap<>();

        UserOrAdminType principal = (UserOrAdminType) SecurityUtils.getSubject().getPrincipal();

        UserExtend currentUser = (UserExtend) principal.getObject();

        Integer userId = currentUser.getId();

        UserLoveCourse userLoveCourse = new UserLoveCourse();


        userLoveCourse.setUserId(userId);

        userLoveCourse.setCourseId(courseId);

        userLoveCourse.setCreateTime(new Date());

        boolean b = userLoveCourseService.addUserLoveCourse(userLoveCourse);

        if(b){
            modelMap.put("success",true);
        }
        return modelMap;
    }

    @RequestMapping(value = "/deleteLoveCourse/{courseId}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> deleteLoveCourse(@PathVariable Integer courseId){
        Map<String,Object> modelMap = new HashMap<>();

        UserOrAdminType principal = (UserOrAdminType) SecurityUtils.getSubject().getPrincipal();

        UserExtend currentUser = (UserExtend) principal.getObject();

        Integer userId = currentUser.getId();

        boolean b = userLoveCourseService.deleteUserLoveCourse(userId,courseId);

        if(b){
            modelMap.put("success",true);
        }
        return modelMap;
    }

    @RequestMapping(value = "checkStatus/{courseId}",method = RequestMethod.GET)
    @ResponseBody
    public boolean checkStatus(@PathVariable Integer courseId){
        UserOrAdminType principal = (UserOrAdminType) SecurityUtils.getSubject().getPrincipal();

        UserExtend currentUser = (UserExtend) principal.getObject();

        Integer userId = currentUser.getId();

        boolean b = userLoveCourseService.getUserLoveCourse(userId,courseId);

        return b;
    }

    @RequestMapping(value = "getUserAllLoveCourse",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getUserAllLoveCourse(){
        Map<String,Object> modelMap = new HashMap<>();

        UserOrAdminType principal = (UserOrAdminType) SecurityUtils.getSubject().getPrincipal();

        System.out.println(principal.getType());

        UserExtend currentUser = (UserExtend) principal.getObject();

        Integer userId = currentUser.getId();

        List<Course> allCourseByLoveCourseList = courseService.getAllCourseByLoveCourseId(userId);
        if(allCourseByLoveCourseList == null){
            modelMap.put("success",false);
        }else{
            modelMap.put("success",true);
            modelMap.put("loveCourseList",allCourseByLoveCourseList);
        }

        return modelMap;
    }
}
