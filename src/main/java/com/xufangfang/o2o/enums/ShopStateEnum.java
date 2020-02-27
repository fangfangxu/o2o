package com.xufangfang.o2o.enums;

public enum ShopStateEnum {
    CHECK(0, "审核中"),
    OFFLINE(-1, "非法店铺"),
    SUCCESS(1, "操作成功"),
    PASS(2, "通过认证"),
    INNER_ERROR(-1001, "内部系统错误"),
    NULL_SHOPID(-1002,"ShopId为空");

    private String stateInfo;
    private int state;

    private ShopStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * 依据传入的state返回响应的enum值
     * @param state
     * @return
     */
    public static ShopStateEnum stateOf(int state){
        for(ShopStateEnum shopStateEnum:values()){
            if(shopStateEnum.getState()==state){
                return shopStateEnum;
            }
        }
        return null;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public int getState() {
        return state;
    }


}
