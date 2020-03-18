package com.xufangfang.o2o.service.impl;

import com.xufangfang.o2o.dao.AreaDao;
import com.xufangfang.o2o.entity.Area;
import com.xufangfang.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("areaService")
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }
}
