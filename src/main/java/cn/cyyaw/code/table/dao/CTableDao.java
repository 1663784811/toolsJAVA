package cn.cyyaw.code.table.dao;

import cn.cyyaw.code.table.entity.CTable;
import cn.cyyaw.jpa.BaseDao;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CTableDao extends BaseDao<CTable, Integer> {

    @Query("select m from CTable m where m.tid = ?1")
    CTable findByTid(String tid);

    @Query("select m from CTable m where m.url=?1 and m.database = ?2 and m.tablename=?3")
    List<CTable> findByDatabaseAndTablename(String url, String db, String table);
}
