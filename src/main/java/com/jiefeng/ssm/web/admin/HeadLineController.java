package com.jiefeng.ssm.web.admin;

import com.jiefeng.ssm.bean.Headlines;
import com.jiefeng.ssm.service.HeadLineService;
import com.jiefeng.ssm.util.ImageUtil;
import com.jiefeng.ssm.util.PathUtil;
import net.sf.json.JSONObject;
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

@Controller
@RequestMapping("/headLine")
public class HeadLineController {

    @Autowired
    private HeadLineService headLineService;

    /**
     * 获取所有头条信息
     * @return
     */
    @RequestMapping(value = "getAllHeadLine",method = RequestMethod.GET)
    @ResponseBody
    public List<Headlines> getAllHeadLine(){
        return headLineService.getAllHeadLine();
    }

    /**
     * 添加头条信息
     * @param map
     * @return
     */
    @RequestMapping(value = "addHeadLine",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addHeadLine(@RequestBody Map map){
        Map<String,Object> modelMap = new HashMap<>();

        JSONObject headLineInfo = JSONObject.fromObject(map.get("headLineInfo"));

        Headlines headlines = (Headlines) JSONObject.toBean(headLineInfo, Headlines.class);

        System.out.println(headlines);

        boolean b = headLineService.addHeadLine(headlines);
        if(b){
            modelMap.put("success",true);
        }else{
            modelMap.put("success",false);
        }


        return modelMap;
    }


    /**
     * 更新头条信息
     * @param headLineId
     * @param map
     * @return
     */
    @RequestMapping(value = "updateHeadLine/{headLineId}",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateHeadLine(@PathVariable Integer headLineId,@RequestBody Map map){
        Map<String,Object> modelMap = new HashMap<>();

        JSONObject headLineInfo = JSONObject.fromObject(map.get("headLineInfo"));

        Headlines headlines = (Headlines) JSONObject.toBean(headLineInfo, Headlines.class);

        headlines.setId(headLineId);

        System.out.println(headlines);

        boolean b = headLineService.updateHeadLine(headlines);
        if(b){
            modelMap.put("success",true);
        }else{
            modelMap.put("success",false);
        }

        return modelMap;
    }


    /**
     * 更换头条封面
     * @param headLineId
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "updateHeadLineImg/{headLineId}",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateHeadLineImg(@PathVariable Integer headLineId, HttpServletRequest request) throws IOException {
        Map<String,Object> modelMap = new HashMap<>();

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> files = multipartRequest.getFiles("headLineImg");

        String path = PathUtil.generateHeadLineImg(headLineId);
        String headLinedPath = ImageUtil.generateNormalImg(files.get(0), path);

        Headlines headlines = new Headlines();

        headlines.setId(headLineId);
        headlines.setImgUrl(headLinedPath);

        boolean b = headLineService.updateHeadLine(headlines);
        if(b){
            modelMap.put("success",true);
        }else{
            modelMap.put("success",false);
        }

        return modelMap;
    }
}
