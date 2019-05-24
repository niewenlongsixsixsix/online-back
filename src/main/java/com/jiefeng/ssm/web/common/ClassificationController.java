package com.jiefeng.ssm.web.common;

import com.jiefeng.ssm.annotation.LoggerOrException;
import com.jiefeng.ssm.bean.Classification;
import com.jiefeng.ssm.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/classification")
public class ClassificationController {

    @Autowired
    private ClassificationService classificationService;

    @RequestMapping(value = "/getAllClassification",method = RequestMethod.GET)
    @ResponseBody
    public List<Classification> getAllClassification(){

        try{
            return classificationService.getAllClassification();
        }catch (Exception e){
            throw e;
        }

    }


    @RequestMapping(value = "/changeClassificationStatus/{categoryId}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> changeClassificationStatus(@PathVariable Integer categoryId){
        Map<String,Object> modelMap = new HashMap<>();

        Classification dataBaseClassificationInfo = classificationService.getClassificationByPrimaryKey(categoryId);

        Integer status = dataBaseClassificationInfo.getStatus();

        Classification updateClassification = new Classification();

        updateClassification.setId(categoryId);

        if(status == 0){
            updateClassification.setStatus(1);
        }else{
            updateClassification.setStatus(0);
        }
        boolean b = classificationService.updateClassification(updateClassification);

        if(b){
            modelMap.put("success",true);
        }else{
            modelMap.put("success",false);
        }

        return modelMap;
    }

    /**
     * 更新分类的名称
     * @param classificationName
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "/changeClassificationName/{categoryId}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> changeClassificationName(@RequestParam String classificationName,@PathVariable Integer categoryId){
        Map<String,Object> modelMap = new HashMap<>();

        Classification classification = new Classification();

        classification.setId(categoryId);

        classification.setName(classificationName);

        boolean b = classificationService.updateClassification(classification);

        if(b){
            modelMap.put("success",true);
        }else{
            modelMap.put("success",false);
        }

        return modelMap;
    }


    /**
     * 添加分类
     * @param classificationName
     * @return
     */
    @RequestMapping(value = "/addClassification",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> addClassification(@RequestParam String classificationName){
        Map<String,Object> modelMap = new HashMap<>();

        Classification classification = new Classification();

        classification.setCreateTime(new Date());

        classification.setName(classificationName);

        boolean b = classificationService.addClassification(classification);

        if(b){
            modelMap.put("success",true);
        }else{
            modelMap.put("success",false);
        }

        return modelMap;
    }

}
