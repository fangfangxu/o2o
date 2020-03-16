package com.xufangfang.o2o.service.impl;

import com.xufangfang.o2o.dao.ShopCategoryDao;
import com.xufangfang.o2o.entity.ShopCategory;
import com.xufangfang.o2o.service.ShopCategaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("shopCategaryService")
public class ShopCategaryServiceImpl implements ShopCategaryService {
  @Autowired
  private ShopCategoryDao shopCategoryDao;

    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        return shopCategoryDao.queryShopCategory(shopCategoryCondition);
    }
}
