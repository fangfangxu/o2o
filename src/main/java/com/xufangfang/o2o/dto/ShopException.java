package com.xufangfang.o2o.dto;


import com.xufangfang.o2o.entity.Shop;
import com.xufangfang.o2o.enums.ShopStateEnum;

import java.util.List;

public class ShopException {
    //结果状态
    private int state;
    //状态标识
    private String stateInfo;
    //店铺数量
    private int count;
    //操作的shop（增删改店铺的时候用到）
    private Shop shop;

    //shop列表
    private List<Shop> shopList;

    public ShopException() {
    }

    //店铺操作失败的时候使用的构造器
    public ShopException(ShopStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    //店铺操成功的时候使用的构造器
    public ShopException(ShopStateEnum stateEnum, Shop shop) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shop = shop;
    }


    //店铺操成功的时候使用的构造器
    public ShopException(ShopStateEnum stateEnum, List<Shop> shopList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopList = shopList;
    }




}
