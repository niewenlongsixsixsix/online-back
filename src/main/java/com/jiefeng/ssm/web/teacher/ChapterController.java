package com.jiefeng.ssm.web.teacher;


import com.jiefeng.ssm.beanExtend.UserExtend;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/chapter")
@Controller
public class ChapterController {

    private Logger logger = LoggerFactory.getLogger(ChapterController.class);

    @RequestMapping(value = "/addSmallerChapter",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addSmallerChapter(HttpServletRequest request){

        Map<String,Object> modelMap = new HashMap<>();

        Subject currentSubject = SecurityUtils.getSubject();

        UserExtend principal = (UserExtend) currentSubject.getPrincipal();
        if(principal != null)
            logger.info("principal id :" + principal.getId() + " username: " + principal.getUsername());

        System.out.println("authenticated? " + currentSubject.isAuthenticated());
        System.out.println("rememberMe? " + currentSubject.isRemembered());

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> files = multipartRequest.getFiles("smallChapterImg");
        String id = request.getParameter("id");

        logger.info("userInfo id: " + id);

        MultipartFile multipartFile = files.get(0);

        logger.info(multipartFile.getOriginalFilename());

        return modelMap;
    }
}
