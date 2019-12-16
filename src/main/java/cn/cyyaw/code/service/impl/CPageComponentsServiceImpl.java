package cn.cyyaw.code.service.impl;

import cn.cyyaw.code.service.CPageComponentsService;
import cn.cyyaw.code.table.dao.CPageComponentsDao;
import cn.cyyaw.code.table.entity.CPageComponents;
import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

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

    public List<CPageComponents> findByExample(CPageComponents cPageComponents) {



        cPageComponentsDao.findAll();


        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                return null;
            }

            @Override
            public Specification and(Specification other) {
                return null;
            }

            @Override
            public Specification or(Specification other) {
                return null;
            }
        };











         return cPageComponentsDao.findAll(Example.of(cPageComponents));
    }
}

