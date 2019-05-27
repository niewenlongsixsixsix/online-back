package com.jiefeng.ssm.web.common;

import com.jiefeng.ssm.annotation.LoggerOrException;
import com.jiefeng.ssm.bean.SmallChapter;
import com.jiefeng.ssm.service.SmallChapterService;
import com.jiefeng.ssm.util.ImageUtil;
import com.jiefeng.ssm.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("smallChapter")
public class SmallChapterController {

    @Autowired
    private SmallChapterService smallChapterService;

    @RequestMapping(value = "/getAllSmallChapterByBigChapterId/{bigChapterId}",method = RequestMethod.GET)
    @ResponseBody
    public List<SmallChapter> getAllSmallChapterByBigChapterId(@PathVariable Integer bigChapterId) throws Exception {
        try{
            return smallChapterService.getAllSmallChapter(bigChapterId);
        }catch (Exception e){
            throw e;
        }

    }

    @RequestMapping(value = {"/addSmallChapter/{bigChapterId}","/updateSmallChapter/{smallChapterId}"},method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> AddOrEditSmallChapter(@PathVariable(required = false) Integer bigChapterId, @PathVariable(required = false) Integer smallChapterId,@RequestBody Map map){
        Map<String,Object> modelMap = new HashMap<>();

        try{
            String title = (String) map.get("title");
            SmallChapter smallChapter = new SmallChapter();
            smallChapter.setTitle(title);

            boolean b;
            if(bigChapterId != null){
                smallChapter.setCreateTime(new Date());
                smallChapter.setVideoUrl("/smallChapterVideo/default_video.mp4");
                smallChapter.setBigChapterId(bigChapterId);
                b = smallChapterService.addSmallChapter(smallChapter);
            }else{
                smallChapter.setId(smallChapterId);
                smallChapter.setBigChapterId(null);
                b = smallChapterService.updateSmallChapter(smallChapter);
            }
        }catch (Exception e){
            throw e;
        }

        modelMap.put("success",true);

        return modelMap;
    }

    @RequestMapping("/updateSmallChapterVideo/{smallChapterId}")
    @ResponseBody
    public Map<String,Object> updateSmallChapterVideo(HttpServletRequest request, @PathVariable Integer smallChapterId) throws IOException {


        Map<String,Object> modelMap = new HashMap<>();

        try{
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> files = multipartRequest.getFiles("smallChapterVideo");

            SmallChapter smallChapter = smallChapterService.getSmallChapterByPrimaryKey(smallChapterId);

            SmallChapter chapter = new SmallChapter();

            chapter.setId(smallChapterId);

            String path = PathUtil.generateSmallVideo(smallChapter.getBigChapterId(),smallChapterId);
            String imgPath = ImageUtil.generateNormalImg(files.get(0), path);
            chapter.setVideoUrl(imgPath);

            boolean b = smallChapterService.updateSmallChapter(chapter);

            if(b)
                modelMap.put("success",true);

        }catch (Exception e){
            throw e;
        }

        modelMap.put("success",false);

        return modelMap;
    }

    @RequestMapping(value = "getCatalogByCourseId/{courseId}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAllChapter(@PathVariable Integer courseId) throws Exception {

        if(courseId == null){
            throw new Exception("参数错误");
        }
        Map<String, Object> allChapter = smallChapterService.getAllChapter(courseId);

        return allChapter;
    }


    @RequestMapping(value = "getSmallChapterByPrimaryKey/{smallChapterId}",method = RequestMethod.GET)
    @ResponseBody
    public SmallChapter getSmallChapterByPrimaryKey(@PathVariable Integer smallChapterId){
        try{
            return smallChapterService.getSmallChapterByPrimaryKey(smallChapterId);
        }catch (Exception e){
            throw e;
        }

    }
}
