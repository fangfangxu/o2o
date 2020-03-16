package com.xufangfang.o2o.dao;

import com.xufangfang.o2o.BaseTest;
import com.xufangfang.o2o.entity.Area;
import com.xufangfang.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShopCategaryDaoTest extends BaseTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Test
    public void testQueryArea(){
        List<ShopCategory> areaList=shopCategoryDao.queryShopCategory(new ShopCategory());
        System.out.print(areaList.size());
    }
}
