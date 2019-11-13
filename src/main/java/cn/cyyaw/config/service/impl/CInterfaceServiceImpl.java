package cn.cyyaw.config.service.impl;

import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import cn.cyyaw.config.service.CInterfaceService;
import cn.cyyaw.config.table.entity.CInterface;
import cn.cyyaw.config.table.dao.CInterfaceDao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class CInterfaceServiceImpl extends BaseService<CInterface, Integer> implements CInterfaceService {

    @Autowired
    private CInterfaceDao cInterfaceDao;

    @Override
    public BaseDao getBaseDao() {
        return cInterfaceDao;
    }

}

