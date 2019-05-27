package com.jiefeng.ssm.util;

import java.io.File;

public class PathUtil {

    /**
     * 获取上传路径根目录
     * @return
     */
    public static String getImgBasePath(){
        String basePath = "D:" + File.separator + "graduation" + File.separator + "online-upload";

        return basePath;
    }

    /**
     * 课程封面保存相对路径
     * @param userId
     * @param courseId
     * @return
     */
    public static String generateCourseImg(Integer userId,Integer courseId){
        String  Path = File.separator + "course" + File.separator + userId + File.separator + courseId + File.separator;
        return Path;
    }

    /**
     * 小节视频保存相对路径
     * @param userId
     * @param smallChapterId
     * @return
     */
    public static String generateSmallVideo(Integer userId,Integer smallChapterId){
        String  Path = File.separator + "smallChapterVideo" + File.separator + userId + File.separator + smallChapterId + File.separator;
        return Path;
    }

    /**
     * 用户头像保存路径
     * @param userId
     * @return
     */
    public static String generateUserHeadImg(Integer userId){
        String  Path = File.separator + "userHeadImg" + File.separator + userId + File.separator;
        return Path;
    }

    /**
     * 头条封面保存路径
     * @param headLineId
     * @return
     */
    public static String generateHeadLineImg(Integer headLineId){
        String  Path = File.separator + File.separator + "headLine" + File.separator + File.separator +  headLineId + File.separator + File.separator;
        return Path;
    }
}
