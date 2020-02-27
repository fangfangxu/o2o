package com.xufangfang.o2o.service;

import com.xufangfang.o2o.dto.ShopExecution;
import com.xufangfang.o2o.entity.Shop;

import java.io.File;

public interface ShopService {
    /**
     * 添加店铺
     * @param shop
     * @param shopImg
     * @return
     */
    ShopExecution addShop(Shop shop, File shopImg);
}
