package com.xufangfang.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/shopadmin", method = {RequestMethod.GET})
public class ShopAdminController {
    @RequestMapping("/shopoperation")
    public String shopOperation() {
        return "shop/shopoperation";
    }


    @RequestMapping("/shoplist")
    public String shoplList() {
        return "shop/shoplist";
    }

    @RequestMapping("/shopmanagement")
    public String shopmanagement() {
        return "shop/shopmanagement";
    }

    @RequestMapping(value = "/productcategorymanagement", method = RequestMethod.GET)
    public String productcategorymanagement() {
        return "shop/productcategorymanagement";
    }

    @RequestMapping(value = "/productoperation")
    public String productOperation() {
        return "shop/productoperation";
    }

}
