package com.jiefeng.ssm.beanExtend;

import com.jiefeng.ssm.bean.SmallChapter;

import java.io.Serializable;
import java.util.List;

public class CourseChapterExtend implements Serializable {

    private String bigChapterName;
    private List<SmallChapter> smallChapters;

    public String getBigChapterName() {
        return bigChapterName;
    }

    public void setBigChapterName(String bigChapterName) {
        this.bigChapterName = bigChapterName;
    }

    public List<SmallChapter> getSmallChapters() {
        return smallChapters;
    }

    public void setSmallChapters(List<SmallChapter> smallChapters) {
        this.smallChapters = smallChapters;
    }

    @Override
    public String toString() {
        return "CourseChapterExtend{" +
                "bigChapterName='" + bigChapterName + '\'' +
                ", smallChapters=" + smallChapters +
                '}';
    }
}
