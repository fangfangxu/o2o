package com.xufangfang.o2o.service;

import com.xufangfang.o2o.BaseTest;
import com.xufangfang.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaServiceTest extends BaseTest {
    @Autowired
    private AreaService areaService;

    @Test
    public void testAreaService() {
        List<Area> list = areaService.getAreaList();
        System.out.println(list.get(0).getAreaName());
    }
}
