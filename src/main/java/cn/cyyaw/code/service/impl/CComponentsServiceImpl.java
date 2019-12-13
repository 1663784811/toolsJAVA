package cn.cyyaw.code.service.impl;

import cn.cyyaw.code.service.CComponentsService;
import cn.cyyaw.code.table.dao.CComponentsDao;
import cn.cyyaw.code.table.entity.CComponents;
import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class CComponentsServiceImpl extends BaseService<CComponents, Integer> implements CComponentsService {

    @Autowired
    private CComponentsDao cComponentsDao;

    @Override
    public BaseDao getBaseDao() {
        return cComponentsDao;
    }

}

