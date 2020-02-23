package com.xufangfang.o2o.dao;

import com.xufangfang.o2o.BaseTest;
import com.xufangfang.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

public class AreaDaoTest extends BaseTest {
    @Autowired
    private AreaDao areaDao;

    @Test
    public void testAreaDao(){
        List<Area> areas= areaDao.queryArea();
       System.out.println(areas.size());
    }


}
