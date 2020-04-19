package cn.cyyaw.sys.service;


import cn.cyyaw.code.table.dao.CFieldDao;
import cn.cyyaw.code.table.dao.CTableDao;
import cn.cyyaw.code.table.entity.CField;
import cn.cyyaw.code.table.entity.CTable;
import cn.cyyaw.util.buildcode.code.OperationTools;
import cn.cyyaw.util.buildcode.database.DataBase;
import cn.cyyaw.util.buildcode.entity.java.JavaColumn;
import cn.cyyaw.util.buildcode.entity.java.JavaData;
import cn.cyyaw.util.tools.WhyStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class DbServiceImpl implements DbService {


    @Autowired
    private CFieldDao cFieldDao;

    @Autowired
    private CTableDao cTableDao;

    @Transactional
    @Override
    public void readstructure(String url, String user, String password) throws SQLException, ClassNotFoundException {
        DataBase dataBase = new DataBase("com.mysql.cj.jdbc.Driver", url, user, password);
        List<JavaData> tableList = dataBase.getTableList(null);
        if (null != tableList && tableList.size() > 0) {
            for (int i = 0; i < tableList.size(); i++) {
                JavaData javaData = tableList.get(i);
                String tableNote = javaData.getTableNote();
                String db = javaData.getDatabase();
                String table = OperationTools.allToLowerCase(javaData.getTable());
                String tableType = OperationTools.allToLowerCase(javaData.getTableType());
                CTable cTable = null;
                List<CTable> cTableList = cTableDao.findByDatabaseAndTablename(url, db, table);
                if (null != cTableList && cTableList.size() > 0) {
                    cTable = cTableList.get(0);
                } else {
                    cTable = new CTable();
                    cTable.setTid(WhyStringUtil.getUUID());
                    cTable.setUrl(url);
                }
                cTable.setUser(user);
                cTable.setPwd(password);
                cTable.setDatabase(db);
                cTable.setTablename(table);
                cTable.setTabletype(tableType);
                cTable.setNote(tableNote);
                CTable cTableSave = cTableDao.save(cTable);
                List<JavaColumn> javaColumns = javaData.getJavaColumns();
                if (null != javaColumns && javaColumns.size() > 0) {
                    for (int j = 0; j < javaColumns.size(); j++) {
                        JavaColumn javaColumn = javaColumns.get(j);
                        String columnName = OperationTools.allToLowerCase(javaColumn.getColumnName());
                        String dbType = OperationTools.allToLowerCase(javaColumn.getDbType());
                        Integer length = javaColumn.getLength();
                        String javaType = OperationTools.allToLowerCase(javaColumn.getJavaType());
                        String defaultValue = javaColumn.getDefaultValue();
                        String note = javaColumn.getNote();
                        Boolean isAutoIncrement = javaColumn.getIsAutoIncrement();
                        Boolean isNull = javaColumn.getIsNull();
                        Boolean isPrimary = javaColumn.getIsPrimary();
                        String pkTableName = OperationTools.allToLowerCase(javaColumn.getPkTableName());
                        String pkColumnName = OperationTools.allToLowerCase(javaColumn.getPkColumnName());
                        Boolean isFk = javaColumn.getIsFk();
                        String fkTableName = OperationTools.allToLowerCase(javaColumn.getFkTableName());
                        String fkColumnName = OperationTools.allToLowerCase(javaColumn.getFkColumnName());
                        Boolean isUnique = javaColumn.getIsUnique();
                        Boolean isIndex = javaColumn.getIsIndex();
                        String indexType = OperationTools.allToLowerCase(javaColumn.getIndexType());
                        String ctableid = cTableSave.getTid();
                        CField cField = null;
                        List<CField> cFieldList = cFieldDao.findByCtableidAndColumnname(ctableid, columnName);
                        if (cFieldList != null && cFieldList.size() > 0) {
                            cField = cFieldList.get(0);
                        } else {
                            cField = new CField();
                            cField.setTid(WhyStringUtil.getUUID());
                        }
                        cField.setCtableid(ctableid);
                        cField.setColumnname(columnName);
                        cField.setDbtype(dbType);
                        cField.setLength(length);
                        cField.setJavatype(javaType);
                        cField.setDefaultvalue(defaultValue);
                        cField.setNote(note);
                        cField.setIsautoincrement(isAutoIncrement ? 1 : 0);
                        cField.setIsnull(isNull ? 1 : 0);
                        cField.setIsprimary(isPrimary ? 1 : 0);
                        cField.setPktablename(pkTableName);
                        cField.setPkcolumnname(pkColumnName);
                        cField.setIsfk(isFk ? 1 : 0);
                        cField.setFktablename(fkTableName);
                        cField.setFkcolumnname(fkColumnName);
                        cField.setIsunique(isUnique ? 1 : 0);
                        cField.setIsindex(isIndex ? 1 : 0);
                        cField.setIndextype(indexType);
                        cFieldDao.save(cField);
                    }
                }
            }
        }
    }
}
