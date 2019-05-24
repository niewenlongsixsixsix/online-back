package com.jiefeng.ssm.service.impl;

import com.jiefeng.ssm.bean.Course;
import com.jiefeng.ssm.bean.SmallChapter;
import com.jiefeng.ssm.beanExtend.BigChapterExtend;
import com.jiefeng.ssm.beanExtend.CourseChapterExtend;
import com.jiefeng.ssm.dao.SmallChapterDao;
import com.jiefeng.ssm.service.BigChapterService;
import com.jiefeng.ssm.service.CourseService;
import com.jiefeng.ssm.service.SmallChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("SmallChapterService")
public class SmallChapterServiceImpl implements SmallChapterService {

    @Autowired
    private SmallChapterDao smallChapterDao;

    @Autowired
    private BigChapterService bigChapterService;

    @Autowired
    private CourseService courseService;

    @Override
    public List<SmallChapter> getAllSmallChapter(Integer bigChapterId) {

        return smallChapterDao.getAllSmallChapterByBigChapterId(bigChapterId);
    }

    @Override
    public boolean addSmallChapter(SmallChapter smallChapter) {

        return smallChapterDao.addSmallChapter(smallChapter);
    }

    @Override
    public boolean updateSmallChapter(SmallChapter smallChapter) {
        return smallChapterDao.updateSmallChapter(smallChapter);
    }

    @Override
    public SmallChapter getSmallChapterByPrimaryKey(Integer smallChapterId) {
        return smallChapterDao.getSmallChapterByPrimaryKey(smallChapterId);
    }

    @Override
    public Map<String,Object> getAllChapter(Integer courseId) {

        Map<String,Object> modelMap = new HashMap<>();

        Course course = courseService.getCourseByPrimaryKey(courseId);

        List<BigChapterExtend> bigChapterExtends = bigChapterService.getBigChapterByCourseId(courseId);

        List<CourseChapterExtend> courseChapterExtends = new ArrayList<>();

        for (BigChapterExtend item : bigChapterExtends) {

            Integer id = item.getId();

            List<SmallChapter> allSmallChapter = smallChapterDao.getAllSmallChapterByBigChapterId(id);

            CourseChapterExtend courseChapterExtend = new CourseChapterExtend();
            courseChapterExtend.setBigChapterName(item.getTitle());
            courseChapterExtend.setSmallChapters(allSmallChapter);

            courseChapterExtends.add(courseChapterExtend);
        }


        modelMap.put("course",course);
        modelMap.put("chapterInfo",courseChapterExtends);
        return modelMap;
    }
}
