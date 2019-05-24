package com.jiefeng.ssm.web.common;

import com.jiefeng.ssm.bean.BigChapter;
import com.jiefeng.ssm.bean.Course;
import com.jiefeng.ssm.bean.SmallChapter;
import com.jiefeng.ssm.bean.User;
import com.jiefeng.ssm.service.BigChapterService;
import com.jiefeng.ssm.service.CourseService;
import com.jiefeng.ssm.service.SmallChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("common")
public class CommonController {


    @Autowired
    private CourseService courseService;

    @Autowired
    private BigChapterService bigChapterService;

    @Autowired
    private SmallChapterService smallChapterService;


    @RequestMapping(value = "/changeStatus/{type}/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> changeUserStatus(@PathVariable Integer type,@PathVariable Integer id){

        // type标识了对课程、章节、或者小节  其中 0 课程  1 章节 2 小节

        Map<String,Object> modelMap = new HashMap<>();

        boolean b = false;

        if(type == 0){
            Course courseByPrimaryKey = courseService.getCourseByPrimaryKey(id);

            Integer keyStatus = courseByPrimaryKey.getStatus();

            Course course = new Course();

            course.setId(id);

            if(keyStatus == 0){
                course.setStatus(1);
            }else{
                course.setStatus(0);
            }

            b = courseService.updateCourse(course);
        }else if(type == 1 ){
            BigChapter oldChapter = bigChapterService.getBigChapterByPrimaryKey(id);

            Integer keyStatus = oldChapter.getStatus();

            BigChapter newChapter = new BigChapter();

            newChapter.setId(id);

            if(keyStatus == 0){
                newChapter.setStatus(1);
            }else{
                newChapter.setStatus(0);
            }

            b = bigChapterService.updateChapter(newChapter);
        }else{
            SmallChapter oldsmallChapter = smallChapterService.getSmallChapterByPrimaryKey(id);

            Integer keyStatus = oldsmallChapter.getStatus();

            SmallChapter newSmallChapter = new SmallChapter();

            newSmallChapter.setId(id);

            if(keyStatus == 0){
                newSmallChapter.setStatus(1);
            }else{
                newSmallChapter.setStatus(0);
            }

            b = smallChapterService.updateSmallChapter(newSmallChapter);
        }

        if(b){
            modelMap.put("success",true);
        }else{
            modelMap.put("success",false);
        }

        return modelMap;
    }

}
