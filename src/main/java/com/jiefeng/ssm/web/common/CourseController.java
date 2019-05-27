package com.jiefeng.ssm.web.common;

import com.jiefeng.ssm.annotation.LoggerOrException;
import com.jiefeng.ssm.bean.Classification;
import com.jiefeng.ssm.bean.Course;
import com.jiefeng.ssm.bean.User;
import com.jiefeng.ssm.beanExtend.UserExtend;
import com.jiefeng.ssm.beanExtend.UserOrAdminType;
import com.jiefeng.ssm.dao.CourseDao;
import com.jiefeng.ssm.service.CourseService;
import com.jiefeng.ssm.util.ImageUtil;
import com.jiefeng.ssm.util.PathUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/course")
@Controller
public class CourseController {

    Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseDao courseDao;

    @RequestMapping(value = {"/getAllCourseByUserId"},method = RequestMethod.GET)
    @ResponseBody
    public List<Course> getAllCourseByUserId() throws Exception {

        System.out.println("jinlaile");
        List<Course> allCourseListByUserId;

        // 0 普通用户 1 管理员查询
        allCourseListByUserId = courseService.getAllCourseListByUserId();

        return allCourseListByUserId;
    }


    @RequestMapping(value = {"/updateImgUrl/{courseId}"},method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateImgUrl(HttpServletRequest request,@PathVariable Integer courseId) throws Exception {

        Map<String,Object> modelMap = new HashMap<>();

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> files = multipartRequest.getFiles("courseImg");

        if(files == null){
            throw new Exception("文件上传为空");
        }

        Course courseByPrimaryKey = courseDao.getCourseByPrimaryKey(courseId);

        Course course = new Course();

        boolean result = false;
        try{
            String path = PathUtil.generateCourseImg(courseByPrimaryKey.getCreateBy().getId(),courseId);
            String imgPath = ImageUtil.generateNormalImg(files.get(0), path);
            course.setImgUrl(imgPath);
            course.setId(courseId);

            result = courseService.updateCourse(course);
        }catch (IOException e){
           throw e;
        }

        modelMap.put("success",true);
        return modelMap;

    }


    /**
     *
     * @param
     * @param addOrEdit true 添加  false修改
     * @param courseId 要修改的用户的ID
     */
    @RequestMapping(value = {"/addCourse/{addOrEdit}","/updateCourse/{addOrEdit}/{courseId}"},method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addOrUpdateCourse(@PathVariable Integer addOrEdit,
                                  @PathVariable(required = false) Integer courseId,
                                  @RequestBody Map map) throws Exception {

        Map<String,Object> modelMap = new HashMap<>();


        System.out.println("addOredit: " + addOrEdit);
        String title = (String) map.get("title");
        Integer classificationId = (Integer) map.get("classification");

        if(title == null || classificationId == null){
            throw new Exception("参数为空");
        }

        logger.info("title: " + title + " classificationId: " + classificationId);
        logger.info(map.toString());

        Course course = new Course();
        course.setTitle(title);
        course.setBelongTo(new Classification(classificationId));

        //判断添加还是更新
        if(addOrEdit == 1){
                Subject subject = SecurityUtils.getSubject();
                UserOrAdminType principal = (UserOrAdminType) subject.getPrincipal();
            UserExtend object = (UserExtend) principal.getObject();
            course.setCreateBy(new User(object.getId()));
                courseService.addCourse(course);
        }else{
            course.setId(courseId);
            courseService.updateCourse(course);
        }
        modelMap.put("success",true);
        return modelMap;
    }


    @RequestMapping(value = "/getCourseByClassification/{classificationId}",method = RequestMethod.GET)
    @ResponseBody
    public List<Course> getClassificationByCategory(@PathVariable Integer classificationId) throws Exception {

        if(classificationId == null){
            throw new Exception("参数为空");
        }

        if(classificationId == 12410){
            return courseService.getAllCourse();
        }else{
            return courseService.getAllCourseByClassification(classificationId);
        }
    }

    @RequestMapping(value = "/getAllCourse",method = RequestMethod.GET)
    @ResponseBody
    @LoggerOrException(operationName = "查询全部课程")
    public List<Course> getAllCourse(){
        return courseService.getAllCourse();
    }


    @RequestMapping(value = "getHotOrNew/{type}",method = RequestMethod.GET)
    @ResponseBody
    public List<Course> getHotOrNew(@PathVariable Integer type){
        return courseService.hotOrNewCourse(type);
    }

}
