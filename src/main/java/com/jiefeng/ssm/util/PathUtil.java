package com.jiefeng.ssm.util;

import java.io.File;

public class PathUtil {

    public static String getImgBasePath(){
        String basePath = "D:" + File.separator + "graduation" + File.separator + "online-upload";

        return basePath;
    }

    public static String generateCourseImg(Integer userId,Integer courseId){
        String  Path = File.separator + "course" + File.separator + userId + File.separator + courseId + File.separator;
        return Path;
    }

    public static String generateSmallVideo(Integer userId,Integer smallChapterId){
        String  Path = File.separator + "smallChapterVideo" + File.separator + userId + File.separator + smallChapterId + File.separator;
        return Path;
    }

    public static String generateUserHeadImg(Integer userId){
        String  Path = File.separator + "userHeadImg" + File.separator + userId + File.separator;
        return Path;
    }
}
