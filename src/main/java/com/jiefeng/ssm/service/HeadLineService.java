package com.jiefeng.ssm.service;

import com.jiefeng.ssm.bean.Headlines;

import java.util.List;

public interface HeadLineService {

    boolean addHeadLine(Headlines headlines);

    boolean updateHeadLine(Headlines headlines);

    List<Headlines> getAllHeadLine();

}
