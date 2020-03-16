package com.xufangfang.o2o.service;

import com.xufangfang.o2o.entity.ShopCategory;

import java.util.List;

public interface ShopCategaryService {
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
