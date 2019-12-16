package cn.cyyaw.code.service.impl;

import cn.cyyaw.code.service.CPageComponentsService;
import cn.cyyaw.code.table.dao.CPageComponentsDao;
import cn.cyyaw.code.table.entity.CPageComponents;
import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class CPageComponentsServiceImpl extends BaseService<CPageComponents, Integer> implements CPageComponentsService {

    @Autowired
    private CPageComponentsDao cPageComponentsDao;

    @Override
    public BaseDao getBaseDao() {
        return cPageComponentsDao;
    }

}

