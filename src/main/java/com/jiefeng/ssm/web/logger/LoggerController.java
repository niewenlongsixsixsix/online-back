package com.jiefeng.ssm.web.logger;

import com.jiefeng.ssm.annotation.ArchivesLog;
import com.jiefeng.ssm.bean.Logger;
import com.jiefeng.ssm.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/logger")
public class LoggerController {

    @Autowired
    private LoggerService loggerService;

    @RequestMapping(value = "/getAllLogger",method = RequestMethod.GET)
    @ResponseBody
    public List<Logger> getAllLogger(){
        return loggerService.getAllLogger();
    }

    @RequestMapping(value = "/testException/{id}",method = RequestMethod.GET)
    @ResponseBody
    @ArchivesLog(operationName = "测试异常")
    public void testException(@PathVariable int id)  {
        if(id == 0){
            try {
                throw new Exception("有异常出现");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
