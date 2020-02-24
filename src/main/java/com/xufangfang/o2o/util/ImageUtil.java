package com.xufangfang.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ImageUtil {


    public static void main(String[] args) throws IOException {
        //获取classPath的绝对值路径
        String basePath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
        Thumbnails.of(new File("C:\\Users\\47284\\Desktop\\weixin.png"))
                .size(500,500).watermark(
                        Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+"\\watermark.jpg")),
                        0.25f
                ).outputQuality(0.8f).toFile("C:\\Users\\47284\\Desktop\\weixin6.png");
    }
}
