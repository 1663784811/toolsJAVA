package cn.cyyaw.code.table.dao;

import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.code.table.entity.CInterface;
import org.springframework.data.jpa.repository.Query;


public interface CInterfaceDao extends BaseDao<CInterface, Integer> {

    @Query("select m from CInterface m where m.tid = ?1")
    CInterface findByTid(String tid);

}
