package com.xufangfang.o2o.dao;

import com.xufangfang.o2o.BaseTest;
import com.xufangfang.o2o.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void testQueryByShopId() throws Exception{
        long shopId=29;
        List<ProductCategory> productCategoryList=productCategoryDao.queryProductCategoryList(shopId);
        System.out.println(productCategoryList.size());
    }
}
