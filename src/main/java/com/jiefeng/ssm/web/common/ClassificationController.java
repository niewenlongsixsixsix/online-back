package com.jiefeng.ssm.web.common;

import com.jiefeng.ssm.bean.Classification;
import com.jiefeng.ssm.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
@RequestMapping("/classification")
public class ClassificationController {

    @Autowired
    private ClassificationService classificationService;

    @RequestMapping(value = "/getAllClassification",method = RequestMethod.GET)
    @ResponseBody
    public List<Classification> getAllClassification(){

        return classificationService.getAllClassification();
    }

}
