package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.bean.Headlines;

import java.util.List;

public interface HeadlinesDao {


    boolean addHeadLine(Headlines headlines);

    boolean updateHeadLine(Headlines headlines);

    List<Headlines> getAllHeadLine();

}
