package com.xufangfang.o2o.dao;

import com.xufangfang.o2o.BaseTest;
import com.xufangfang.o2o.entity.Area;
import com.xufangfang.o2o.entity.PersonInfo;
import com.xufangfang.o2o.entity.Shop;
import com.xufangfang.o2o.entity.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;

    @Test
    @Ignore
    public void testInsertShop() {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(10L);
        shop.setOwner(owner);
        shop.setShopCategory(shopCategory);
        shop.setArea(area);
        shop.setShopName("测试的店铺");
        shop.setShopDesc("testxu");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        int effectedNum=shopDao.insertShop(shop);
    }
    @Test
    public void testUpdateShop() {
        Shop shop = new Shop();
        shop.setShopId(41L);
        shop.setShopDesc("测试描述");
        shop.setShopAddr("测试地址");
        shop.setLastEditTime(new Date());
        int effectedNum=shopDao.updateShop(shop);
    }


}
