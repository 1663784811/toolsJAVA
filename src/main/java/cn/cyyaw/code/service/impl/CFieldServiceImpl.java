package cn.cyyaw.code.service.impl;

import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import cn.cyyaw.code.service.CFieldService;
import cn.cyyaw.code.table.entity.CField;
import cn.cyyaw.code.table.dao.CFieldDao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class CFieldServiceImpl extends BaseService<CField, Integer> implements CFieldService {

    @Autowired
    private CFieldDao cFieldDao;

    @Override
    public BaseDao getBaseDao() {
        return cFieldDao;
    }

}

