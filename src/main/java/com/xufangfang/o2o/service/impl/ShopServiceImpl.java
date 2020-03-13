package com.xufangfang.o2o.service.impl;

import com.xufangfang.o2o.dao.ShopDao;
import com.xufangfang.o2o.dto.ShopExecution;
import com.xufangfang.o2o.entity.Shop;
import com.xufangfang.o2o.enums.ShopStateEnum;
import com.xufangfang.o2o.exceptions.ShopOperationException;
import com.xufangfang.o2o.service.ShopService;
import com.xufangfang.o2o.util.ImageUtil;
import com.xufangfang.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

@Service("shopService")
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, File shopImg) {
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            //给店铺信息赋初始值
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            //添加店铺信息
            int effectedNum = shopDao.insertShop(shop);
            if (effectedNum <= 0) {
                throw new RuntimeException("店铺创建失败");
            } else {
                if (shopImg != null) {
                    //存储图片
                    try {
                        addShopImg(shop, shopImg);
                    } catch (Exception e) {
                        throw new RuntimeException("addShopImg error:" + e.getMessage());
                    }
                    effectedNum = shopDao.updateShop(shop);
                    if (effectedNum <= 0) {
                        throw new RuntimeException("更新图片地址失败");
                    }


                }

            }


        } catch (Exception e) {
            throw new RuntimeException("addShop error：" + e.getMessage());
        }
        return null;
    }

    
    private void addShopImg(Shop shop,File shopImg){


    }



}
