package cn.cyyaw.util.buildcode.database;


import cn.cyyaw.util.buildcode.config.DataConfig;
import cn.cyyaw.util.buildcode.entity.java.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

@Slf4j
public class DataBaseTest {

    DataBase base;

    @Before
    public void before() throws SQLException, ClassNotFoundException {
        base = new DataBase(DataConfig.DRIVER, DataConfig.URL, DataConfig.USER, DataConfig.PWD);
    }


    @Test
    public void test01() throws SQLException {
        List<ForeignKey> foreignKeyList = base.getForeignKeys("t_admin_role");
        if (foreignKeyList != null && foreignKeyList.size() > 0) {
            for (int i = 0; i < foreignKeyList.size(); i++) {
                ForeignKey foreignKey = foreignKeyList.get(i);
                log.info("外键：表名：{}  ,列名：{}", foreignKey.getPkTableName(), foreignKey.getPkColumnName());
            }
        }
    }

    @Test
    public void test02() throws SQLException {
        List<PrimaryKeys> list = base.getPrimaryKeys("t_admin");
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                PrimaryKeys obj = list.get(i);
                log.info("主键：表名：{}  ,列名：{}", obj.getTableName(), obj.getColumnName());
            }
        }
    }

    @Test
    public void test03() throws SQLException {
        List<IndexKey> list = base.getIndexList("t_admin");
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                IndexKey obj = list.get(i);
                log.info("索引：表名：{}  ,列名：{}, 是否唯一：{},类型：{}", obj.getTableName(), obj.getColumnName(), obj.getIsUnique(), obj.getIndexType());
            }
        }
    }

    @Test
    public void test04() throws SQLException {
        List<JavaColumn> list = base.getColumnList("t_admin");
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                JavaColumn obj = list.get(i);
                log.info("索引：列名：{} \t\t\t\t ,类型：{},\t\t\t\t 是否唯一：{},\t\t\t\t类型：{}", obj.getColumnName(), obj.getDbType(), obj.getIsUnique(), obj.getIndexType());
            }
        }
    }

    @Test
    public void test05() throws SQLException {
        List<JavaData> list = base.getTableList(null);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                JavaData obj = list.get(i);
                log.info("索引：数据库：{} ,表名：{}  ,注释：{}, 类型：{}", obj.getDatabase(), obj.getTable(), obj.getTableNote(), obj.getTableType());
            }
        }
    }


}















