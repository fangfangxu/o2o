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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
        shop.setShopName("测试的店铺202003156");
        shop.setShopDesc("testxu331");
        shop.setShopAddr("test1133");
        shop.setPhone("test133");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg = new File("E:/image/6cfa6944-6ba4-4f79-9813-4f0621d50838.JPG");
        InputStream ins = null;
        try {
            ins = new FileInputStream(shopImg);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ShopExecution se = shopService.addShop(shop, ins, shopImg.getName());
        System.out.println(se.getState());
    }

    @Test
    public void testModifiedShop(){
        Shop shop=shopService.getByShopId(95);
        shop.setShopName("修改后的图片");
        File shopImg = new File("E:/照片/Camera/11.jpg");
        InputStream ins = null;
        try {
            ins = new FileInputStream(shopImg);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        shopService.modifyShop(shop,ins,"dabai.JPG");


    }
}
