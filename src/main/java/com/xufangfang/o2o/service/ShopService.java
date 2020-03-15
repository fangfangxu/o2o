package com.xufangfang.o2o.service;

import com.xufangfang.o2o.dto.ShopExecution;
import com.xufangfang.o2o.entity.Shop;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    /**
     * 添加店铺
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     * @return
     */
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String fileName) ;
}
