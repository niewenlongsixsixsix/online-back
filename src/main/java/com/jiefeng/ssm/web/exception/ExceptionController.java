package com.jiefeng.ssm.web.exception;

import com.jiefeng.ssm.bean.Exception;
import com.jiefeng.ssm.service.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("exception")
public class ExceptionController {

    @Autowired
    private ExceptionService exceptionService;

    @RequestMapping(value = "/getAllException",method = RequestMethod.GET)
    @ResponseBody
    public List<Exception> getAllException(){
        return exceptionService.getAllException();
    }


}
