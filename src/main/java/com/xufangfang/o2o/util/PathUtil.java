package com.xufangfang.o2o.util;

/**
 * 路径处理工具类
 * (1)根据执行环境的不同，提供不同的根路径
 */
public class PathUtil {
    //System.getProperty获取系统的属性
    //file.seperator获取系统文件的分隔符
    private static String seperator = System.getProperty("file.seperator");

    public static String getImgBasePath() {
        //System.getProperty获取系统的属性
        //os.name获取系统的名称
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "E:/资料/img/";
        } else {
            basePath = "/home/xiangze/image/";
        }
        //路径中的分隔符对于Linux是反斜杠、对于windows是斜杠，需要对分隔符进行处理
        basePath = basePath.replace("/", seperator);
        return basePath;
    }

    /**
     * 获取店铺图片存储路径
     * 业务需要：将店铺图片分别存储在各自店铺的路径下
     *
     * @return
     */
    public static String getShopImagePath(long shopId) {
        String imagePath = "/upload/item/shop/" + shopId + "/";
        return imagePath.replace("/", seperator);
    }

}
