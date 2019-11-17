package cn.cyyaw.code.table.dao;

import cn.cyyaw.code.table.entity.CField;
import cn.cyyaw.jpa.BaseDao;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CFieldDao extends BaseDao<CField, Integer> {

    @Query("select m from  CField m where m.tid=?1")
    CField findByTid(String tid);

    @Query("select m from  CField m where m.ctableid=?1 and m.columnname=?2")
    List<CField> findByCtableidAndColumnname(String ctableid, String columnName);

    @Query("select m from CField m where m.ctableid =?1")
    List<CField> findByCtableid(String ctableid);

}
