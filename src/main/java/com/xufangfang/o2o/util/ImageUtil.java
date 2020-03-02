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

    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();

    /**
     * CommonsMultipartFile 转换成 File
     *
     * @param cFile
     * @return
     */
    public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile) {
        File newFile = new File(cFile.getOriginalFilename());
        try {
            cFile.transferTo(newFile);
        } catch (IllegalStateException e) {
            logger.error(e.toString());
            e.printStackTrace();

        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return newFile;
    }


    /**
     * @param thumbnail  处理缩略图、并返回新生成图片的相对值路径
     * @param targetAddr 将文件保存到哪里
     * @return 返回相对地址
     * CommonsMultipartFile 后端没有能初始化他的方式、只能前端文件上传调用后端时，才触发
     * 构造方法进行初始化，改成File更合适一点、但CommonsMultipartFile
     * 可以直接转换成File
     * 在调用这个函数之前我们需要把  CommonsMultipartFile 转换成 File
     */
    public static String generateThumbnail(InputStream thumbnail, String fileName,String targetAddr) {
        //获取随机名
        String realFileName = getRandomFileName();
        //获取扩展名
        String extension = getFileExtension(fileName);
        //若文件目录不存在、则创建出来
        makeDirPath(targetAddr);
        //相对路径
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current relativeAddr is :" + relativeAddr);
        //最后的文件路径=根路径+相对路径
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete addr is :" + PathUtil.getImgBasePath() + relativeAddr);
        //创建缩略图
        try {
//            Thumbnails.of(thumbnail.getInputStream())
            Thumbnails.of(thumbnail)
                    .size(200, 200)
                    //添加水印
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "\\watermark.jpg")),
                            0.25f)
                    //压缩图片
                    .outputQuality(0.8f)
                    //指定保存到哪个文件里
                    .toFile(dest);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        //返回相对地址
        return relativeAddr;
    }

    /**
     * 生成随机文件名，当前年月日时分秒+五位随机数
     *
     * @return
     */
    public static String getRandomFileName() {
        //获取随机五位数:10000~99999之间
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }

    /**
     * 获取输入文件流的扩展名
     *
     * @param fileName
     * @return
     */
    private static String getFileExtension(String fileName) {
        //获取输入文件流的文件名
        return fileName.substring(fileName.lastIndexOf("."));
    }


    /**
     * 创建目标路径所涉及到的目录，即/home/work/fangfang/xxx.jpg,
     * 那么 home work fangfang 这三个文件夹都得自动创建
     * /home/work/fangfang/xxx.jpg 是目标文件所属的文件夹的相对路径
     *
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        //获取目标文件要存储的绝对路径(全路径)
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }


    }


    public static void main(String[] args) throws IOException {
        //获取classPath的绝对值路径 /target/classes
        //0.25f：定义透明度
        //压缩图片压缩比：0.8f
        Thumbnails.of(new File("C:\\Users\\47284\\Desktop\\weixin.png"))
                .size(500, 500).watermark(
                Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "\\watermark.jpg")),
                0.25f
        ).outputQuality(0.8f).toFile("C:\\Users\\47284\\Desktop\\weixin6.png");
    }
}
