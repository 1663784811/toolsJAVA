package cn.cyyaw.util.buildcode;

import cn.cyyaw.util.buildcode.entity.database.DataBase;
import cn.cyyaw.util.buildcode.entity.java.ForeignKey;
import cn.cyyaw.util.buildcode.entity.java.JavaColumn;
import cn.cyyaw.util.buildcode.entity.java.JavaData;
import cn.cyyaw.util.buildcode.entity.java.PrimaryKeys;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class testDataBase {


    /**
     * 获取数据表数据
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void test01() throws SQLException, ClassNotFoundException {
        DataBase dataBase = new DataBase();
        List<JavaData> tableList = dataBase.getTableList();
        for (int i = 0; i < tableList.size(); i++) {
            JavaData javaData = tableList.get(i);
            System.out.println("=======================");
            System.out.println("表名：" + javaData.getTableNote());
            System.out.println("类型：" + javaData.getTableType());
            System.out.println("注释：" + javaData.getTableNote());
            System.out.println("字段列表===");
            List<JavaColumn> javaColumns = javaData.getJavaColumns();
            for (int j = 0; j < javaColumns.size(); j++) {
                JavaColumn javaColumn = javaColumns.get(j);
                System.out.println("==============");
                System.out.println("=====字段名：" + javaColumn.getName());
                System.out.println("=====注释：" + javaColumn.getNote());
                System.out.println("=====数据库类型：" + javaColumn.getDbType());
                System.out.println("=====java类型：" + javaColumn.getJavaType());
                System.out.println("=====大小：" + javaColumn.getLenth());
                System.out.println("=====是否自增：" + javaColumn.getAutoIncrement());
                System.out.println("=====是否是主键：" + javaColumn.getPrimary());
                System.out.println("=====是否是外键：" + javaColumn.getFktable());
                System.out.println("=====外键指向的表：" + javaColumn.getPkTableName());
                System.out.println("=====外键指向的字段：" + javaColumn.getPkTableColumn());
                System.out.println("==============");
            }
        }
    }

    /**
     * 获取主键列表
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void test02() throws SQLException, ClassNotFoundException {
        System.out.println("主键列表====================================");
        DataBase dataBase = new DataBase();
        List<PrimaryKeys> primaryKeys = dataBase.getPrimaryKeys("t_admin");
        for (int i = 0; i < primaryKeys.size(); i++) {
            System.out.println(primaryKeys.get(i).getColumnName());
        }

    }

    /**
     * 获取外键列表
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void test03() throws SQLException, ClassNotFoundException {
        DataBase dataBase = new DataBase();
        System.out.println("外键列表====================================");
        List<ForeignKey> t_admin = dataBase.getForeignKeys("t_role_power");
        for (int i = 0; i < t_admin.size(); i++) {
            System.out.println(t_admin.get(i).getPkTableName());
            System.out.println(t_admin.get(i).getPkColumnName());
            System.out.println(t_admin.get(i).getPkTableNote());
        }
    }
}
