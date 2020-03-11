package com.xufangfang.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class ImageUtil {
   private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

     public static String generateThumbnail(CommonsMultipartFile thumbnail,String targetAddr){
       //获取文件随机名
       String realFileName=getRandomFileName();
       //获取文件扩展名
       String extension=getFileExtension(thumbnail);
       makeDirPath(targetAddr);
       String relativeAddr=targetAddr+realFileName+extension;
       File dest=new File(PathUtil.getImgBasePath()+relativeAddr);
       try{
           Thumbnails.of(thumbnail.getInputStream()).size(200,200).watermark(Positions.BOTTOM_RIGHT,
                   ImageIO.read(new File(basePath+"/watermark.jpg")),0.25f).outputQuality(0.8f)
           .toFile(dest);

       }catch (IOException e){
           e.printStackTrace();
       }
    }

    /**
     * 生成随机文件名
     * @return
     */
   private static String getRandomFileName(){
         return null;
   }


    public static void main(String[] args) throws IOException {
        /**
         * 1、输入的文件是什么
         * 2、输出的文件是什么
         */
        //获取ClassPath的绝对值路径
        Thumbnails.of(new File("E:\\资料\\1580800005(1).png"))
                .size(200, 200).watermark(Positions.BOTTOM_RIGHT,
                ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f).outputQuality(0.8f)
                .toFile("E:\\资料\\xufangfang.png");
    }


}
