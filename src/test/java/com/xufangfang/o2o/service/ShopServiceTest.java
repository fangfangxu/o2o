package com.xufangfang.o2o.service;

import com.xufangfang.o2o.BaseTest;
import com.xufangfang.o2o.dto.ShopExecution;
import com.xufangfang.o2o.entity.Area;
import com.xufangfang.o2o.entity.PersonInfo;
import com.xufangfang.o2o.entity.Shop;
import com.xufangfang.o2o.entity.ShopCategory;
import com.xufangfang.o2o.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Date;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void testAddShop() {
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
        shop.setShopName("测试的店铺111111");
        shop.setShopDesc("testxu111");
        shop.setShopAddr("test111");
        shop.setPhone("test111");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg = new File("C:\\Users\\47284\\Desktop\\weixin.png");
        ShopExecution se= shopService.addShop(shop,shopImg);
    }
}
