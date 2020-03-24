package com.xufangfang.o2o.service.impl;

import com.xufangfang.o2o.dao.ShopDao;
import com.xufangfang.o2o.dto.ShopExecution;
import com.xufangfang.o2o.entity.Shop;
import com.xufangfang.o2o.enums.ShopStateEnum;
import com.xufangfang.o2o.exceptions.ShopOperationException;
import com.xufangfang.o2o.service.ShopService;
import com.xufangfang.o2o.util.ImageUtil;
import com.xufangfang.o2o.util.PageCalculator;
import com.xufangfang.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Service("shopService")
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
        int count = shopDao.queryShopCount(shopCondition);
        ShopExecution se = new ShopExecution();
        if (shopList != null) {
            se.setCount(count);
            se.setShopList(shopList);
        } else {
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return se;
    }

    @Override
    public Shop getByShopId(long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException {
        if (shop == null || shop.getShopId() == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        } else {
            //1、判断是否需要处理图片：若更新图片则删除旧的图片信息
            try {
                if (shopImgInputStream != null && !StringUtils.isEmpty(fileName)) {
                    //先获取Shop之前的图片地址
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                    if (tempShop.getShopImg() != null) {
                        ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                    }
                    addShopImg(shop, shopImgInputStream, fileName);
                }

                //2、更新店铺信息
                shop.setLastEditTime(new Date());
                int effectedNum = shopDao.updateShop(shop);
                if (effectedNum <= 0) {
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                } else {
                    shop = shopDao.queryByShopId(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS, shop);
                }
            } catch (Exception e) {
                throw new ShopOperationException("MODIFYsHOP ERROR:" + e.getMessage());
            }
        }

    }

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
                        addShopImg(shop, shopImgInputStream, fileName);
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
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }


    private String addShopImg(Shop shop, InputStream shopImgInputStream, String fileName) {
        //相对值路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        //处理缩略图、并返回新生成图片的相对值路径
        String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream, fileName, dest);
        shop.setShopImg(shopImgAddr);
        return shopImgAddr;
    }


}
