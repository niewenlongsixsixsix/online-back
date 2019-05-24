package com.jiefeng.ssm.util.beanExtendUtil;

import com.jiefeng.ssm.bean.BigChapter;
import com.jiefeng.ssm.beanExtend.BigChapterExtend;

import java.util.ArrayList;
import java.util.List;

public class BigChapterUtil {

    public static List<BigChapterExtend> getBigChapterExtendList(List<BigChapter> bigChapterList){

        List<BigChapterExtend> list = new ArrayList<>();

        for (BigChapter bigChapter : bigChapterList) {
            BigChapterExtend bigChapterExtend = new BigChapterExtend();
            bigChapterExtend.setId(bigChapter.getId());
            bigChapterExtend.setTitle(bigChapter.getTitle());
            bigChapterExtend.setStatus(bigChapter.getStatus());
            bigChapterExtend.setDescribe(bigChapter.getChapterDescribe());
            bigChapterExtend.setCreateTime(bigChapter.getCreateTime());
            list.add(bigChapterExtend);
        }
        return list;
    }
}
