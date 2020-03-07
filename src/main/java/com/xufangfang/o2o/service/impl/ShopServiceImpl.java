package com.xufangfang.o2o.service.impl;

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

import java.io.InputStream;
import java.util.Date;

@Service("shopService")
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, InputStream shopImg,String fileName) {
        //空值判断
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        //添加店铺需要以下几步，以下几步都有可能出错
        try {
            //1、添加店铺信息
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            int effectedNum = shopDao.insertShop(shop);
        //2、
            if (effectedNum <= 0) {
                throw new ShopOperationException("店铺创建失败");
            } else {
                //判断传入的文件是否为空，不为空将图片存储到对应的目录里边
                if (shopImg != null) {
                    //存储图片, 成功后将图片地址更新到Shop中
                    try {
                        addShopImg(shop, shopImg,fileName);
                    } catch (Exception e) {
                        throw new ShopOperationException("addShopImg error:" + e.getMessage());
                    }

                    //更新店铺的图片地址
                    effectedNum = shopDao.updateShop(shop);
                    if (effectedNum <= 0) {
                        throw new ShopOperationException("更新图片地址失败");
                    }
                }
            }
        } catch (Exception e) {
            //必须当且仅当抛出ShopOperationException或者继承
            //ShopOperationException时，事务才会得以终止并回滚。
            //如果是Exception，那么事务是没办法终止，该提交
            //的就已经提交了并不会回滚
            throw new ShopOperationException("addShop error" + e.getMessage());
        }

        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }

    private void addShopImg(Shop shop, InputStream shopImg,String fileName) {
        String path = ImageUtil.generateThumbnail(shopImg, fileName,PathUtil.getShopImagePath(shop.getShopId()));
        shop.setShopImg(path);
    }
}
