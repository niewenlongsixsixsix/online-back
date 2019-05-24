package com.jiefeng.ssm.util.beanExtendUtil;

import com.jiefeng.ssm.bean.SmallChapter;
import com.jiefeng.ssm.beanExtend.BigChapterExtend;
import com.jiefeng.ssm.beanExtend.CourseChapterExtend;
import com.jiefeng.ssm.service.SmallChapterService;
import com.jiefeng.ssm.service.impl.SmallChapterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChapterUtil {

    private  SmallChapterService smallChapterService = new SmallChapterServiceImpl();

    public  List<CourseChapterExtend> getAllChapter(List<BigChapterExtend> bigChapterExtendList){
        List<CourseChapterExtend> courseChapterExtends = new ArrayList<>();

        for (BigChapterExtend item : bigChapterExtendList) {

            Integer id = item.getId();

            List<SmallChapter> allSmallChapter = smallChapterService.getAllSmallChapter(id);

            CourseChapterExtend courseChapterExtend = new CourseChapterExtend();
            courseChapterExtend.setBigChapterName(item.getTitle());
            courseChapterExtend.setSmallChapters(allSmallChapter);

            courseChapterExtends.add(courseChapterExtend);
        }


        return courseChapterExtends;
    }
}
