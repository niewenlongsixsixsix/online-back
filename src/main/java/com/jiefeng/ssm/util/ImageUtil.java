
package com.jiefeng.ssm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static Random r = new Random();
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    /**
     * 处理详情图，并返回新生成图片的相对值路径
     *
     * @param file
     * @param targetAddr
     * @return
     */
    public static String generateNormalImg(MultipartFile file, String targetAddr) throws IOException {
        // 获取不重复的随机名
        String realFileName = getRandomFileName();
        // 获取文件的扩展名如png,jpg等
        String extension = getFileExtension(file.getOriginalFilename());
        // 如果目标路径不存在，则自动创建
        makeDirPath(targetAddr);
        // 获取文件存储的相对路径(带文件名)
        String relativeAddr = targetAddr + realFileName + extension;
        logger.info("current relativeAddr is :" + relativeAddr);
        // 获取文件要保存到的目标路径
        File dest;
        dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.info("current complete addr is :" + PathUtil.getImgBasePath() + relativeAddr);
        file.transferTo(dest);
        // 返回图片相对路径地址
        return relativeAddr;
    }


    /**
     * 创建目标路径所涉及到的目录，即/home/work/xiangze/xxx.jpg
     * 那么 home,work,xiangze这三个文件夹都得自动创建
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        // TODO Auto-generated method stub
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if(!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    /**
     * 获取输入文件流的扩展名
     * @param fileName
     * @return
     */
    private static String getFileExtension(String fileName) {
        // TODO Auto-generated method stub
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生成随机文件名
     * @return
     */
    public static String getRandomFileName() {
        // TODO Auto-generated method stub
        //获取随机的五位数
        int ranNum = r.nextInt(89999)+10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + ranNum;
    }

    /**
     * storePath是文件的路径还是目录的路径
     * 如果storePath是文件路径则删除文件
     * 如果storePath是目录路径则删除该目录下的所有图片
     * @param storePath
     */
    public static void deleteFileOrPath(String storePath) {
        File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
        if(fileOrPath.exists()) {
            if(fileOrPath.isDirectory()) {
                File[] files = fileOrPath.listFiles();
                for(int i = 0; i < files.length; i++) {
                    files[i].delete();
                }
            }
            fileOrPath.delete();
        }
    }

}
