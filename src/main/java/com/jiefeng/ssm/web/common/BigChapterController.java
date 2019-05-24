package com.jiefeng.ssm.web.common;

import com.jiefeng.ssm.annotation.LoggerOrException;
import com.jiefeng.ssm.bean.BigChapter;
import com.jiefeng.ssm.bean.Course;
import com.jiefeng.ssm.beanExtend.BigChapterExtend;
import com.jiefeng.ssm.service.BigChapterService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bigChapter")
public class BigChapterController {

    Logger logger = LoggerFactory.getLogger(BigChapterController.class);

    @Autowired
    private BigChapterService bigChapterService;

    @RequestMapping(value = "getAllBigChapterByCourseId/{courseId}",method = RequestMethod.GET)
    @ResponseBody
    public List<BigChapterExtend> getAllBigChapterByCourseId(@PathVariable Integer courseId){
        List<BigChapterExtend> bigChapterByCourseId = bigChapterService.getBigChapterByCourseId(courseId);
        return bigChapterByCourseId;
    }

    @RequestMapping(value = "addOrEditBigChapter/{addOrEdit}",method = RequestMethod.POST)
    @ResponseBody
    public boolean addOrEditBigChapter(@PathVariable Integer addOrEdit, @RequestBody Map map) throws Exception {

        JSONObject object = JSONObject.fromObject(map.get("BigChapterInfo"));
        if(object.isEmpty()){
            throw new Exception("参数为空");
        }
        BigChapter bigChapter = (BigChapter) JSONObject.toBean(object, BigChapter.class);

        logger.info("Big Chapter Info: " + bigChapter);

        boolean b;

        if(addOrEdit == 1){
            Integer courseId = Integer.parseInt((String) map.get("courseId"));
            bigChapter.setCreateTime(new Date());
            bigChapter.setCourseId(new Course(courseId));
            b = bigChapterService.addBigChapter(bigChapter);
        }else{
            Integer bigChapterId = (Integer) map.get("bigChapterId");
            bigChapter.setId(bigChapterId);
            b = bigChapterService.updateChapter(bigChapter);
        }
        return b;
    }

}
