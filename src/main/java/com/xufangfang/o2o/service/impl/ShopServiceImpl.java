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
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) {
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
                throw new ShopOperationException("店铺创建失败");
            } else {
                if (shopImgInputStream != null) {
                    //存储图片
                    try {
                        addShopImg(shop, shopImgInputStream,fileName);
                    } catch (Exception e) {
                        throw new ShopOperationException("addShopImg error:" + e.getMessage());
                    }
                    effectedNum = shopDao.updateShop(shop);
                    if (effectedNum <= 0) {
                        throw new ShopOperationException("更新图片地址失败");
                    }


                }

            }


        } catch (Exception e) {
            throw new ShopOperationException("addShop error：" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.SUCCESS,shop);
    }


    private String addShopImg(Shop shop, InputStream shopImgInputStream,String fileName) {
        //相对值路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        //处理缩略图、并返回新生成图片的相对值路径
        String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream, fileName,dest);
        shop.setShopImg(shopImgAddr);
        return shopImgAddr;
    }


}
