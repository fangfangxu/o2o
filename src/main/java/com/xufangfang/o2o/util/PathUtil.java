package com.xufangfang.o2o.util;

/**
 * 路径处理工具类
 */
public class PathUtil {
    //获取文件的分隔符
    private static String seperator = System.getProperty("file.separator");

    /**
     * 获取文件输出路径
     *
     * @return
     */
    public static String getImgBasePath() {
        //获取操作系统的名称
        String os = System.getProperty("os.name");
        System.out.println(os);
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "D:/projectdev/image/";
        } else {
            basePath = "/home/fangfang/image";
        }
        //处理windows和Linux路径的斜杠问题：
        basePath = basePath.replace("/", seperator);
        return basePath;
    }

    /**
     * 获取店铺图片的存储路径
     * 将这些图片分别存储在各自店铺的路径下
     */
    public static String getShopImagePath(long shopId) {
        String imagePath = "upload/item/shop/" + shopId + "/";
        return imagePath.replace("/", seperator);
    }

}
