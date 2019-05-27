package com.jiefeng.ssm.service.impl;

import com.jiefeng.ssm.bean.Headlines;
import com.jiefeng.ssm.dao.HeadlinesDao;
import com.jiefeng.ssm.service.HeadLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("HeadLineService")
public class HeadLineServiceImpl implements HeadLineService {

    @Autowired
    private HeadlinesDao headlinesDao;

    @Override
    public boolean addHeadLine(Headlines headlines) {
        return headlinesDao.addHeadLine(headlines);
    }

    @Override
    public boolean updateHeadLine(Headlines headlines) {
        return headlinesDao.updateHeadLine(headlines);
    }

    @Override
    public List<Headlines> getAllHeadLine() {
        return headlinesDao.getAllHeadLine();
    }
}
