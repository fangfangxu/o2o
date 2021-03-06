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
import java.util.List;

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
        shop.setShopDesc("xuxuxuxux");
        shop.setShopAddr("xuxuxuxux");
        shop.setPhone("xuxuxuxux");
        shop.setShopImg("xuxuxuxux");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        int effectedNum = shopDao.insertShop(shop);
        System.out.println(effectedNum);
    }


    @Test
    @Ignore
    public void testUpdateShop() {
        Shop shop = new Shop();
        shop.setShopId(63L);
        shop.setShopDesc("徐方方描述");
        shop.setShopAddr("徐方方测试地址");
        shop.setLastEditTime(new Date());
        int effectedNum = shopDao.updateShop(shop);
        System.out.println(effectedNum);

    }

    @Test
    public void testQueryShop() {
        Shop Shop = shopDao.queryByShopId(87);
        System.out.print(Shop);
    }

    @Test
    public void testQueryShopList(){
        Shop shopCondition=new Shop();
        PersonInfo owner=new PersonInfo();
        owner.setUserId(1L);
        shopCondition.setOwner(owner);
        ShopCategory shopCategory=new ShopCategory();
        shopCategory.setShopCategoryId(22L);
        shopCondition.setShopCategory(shopCategory);
        List<Shop> shopList= shopDao.queryShopList(shopCondition,0,5);
        System.out.println(shopList.size());
        System.out.println(shopDao.queryShopCount(shopCondition));
    }

}
