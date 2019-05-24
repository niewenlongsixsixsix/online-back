package com.jiefeng.ssm.service.impl;

import com.jiefeng.ssm.bean.BigChapter;
import com.jiefeng.ssm.beanExtend.BigChapterExtend;
import com.jiefeng.ssm.dao.BigChapterDao;
import com.jiefeng.ssm.service.BigChapterService;
import com.jiefeng.ssm.util.beanExtendUtil.BigChapterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BigChapterService")
public class BigChapterServiceImpl implements BigChapterService {

    @Autowired
    private BigChapterDao bigChapterDao;

    /**
     * 根据课程id获取其章节ID
     * @param courseId
     * @return
     */
    @Override
    public List<BigChapterExtend> getBigChapterByCourseId(Integer courseId) {
        List<BigChapter> bigChapterList = bigChapterDao.getAllBigChapterByCourseId(courseId);
        return BigChapterUtil.getBigChapterExtendList(bigChapterList);
    }

    /**
     * 添加大章节
     * @param bigChapter
     * @return
     */
    @Override
    public boolean addBigChapter(BigChapter bigChapter) {

        return bigChapterDao.addBigChapter(bigChapter);
    }

    /**
     * 更新大章节
     * @param bigChapter
     * @return
     */
    @Override
    public boolean updateChapter(BigChapter bigChapter) {

        return bigChapterDao.updateBigChapter(bigChapter);
    }

    @Override
    public BigChapter getBigChapterByPrimaryKey(Integer id) {
        return bigChapterDao.getBigChapterByPrimaryKey(id);
    }
}
