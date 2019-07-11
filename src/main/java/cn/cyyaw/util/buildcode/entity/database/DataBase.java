package cn.cyyaw.util.buildcode.entity.database;



import cn.cyyaw.util.buildcode.code.TypeTools;
import cn.cyyaw.util.buildcode.config.DataConfig;
import cn.cyyaw.util.buildcode.entity.java.ForeignKey;
import cn.cyyaw.util.buildcode.entity.java.JavaColumn;
import cn.cyyaw.util.buildcode.entity.java.JavaData;
import cn.cyyaw.util.buildcode.entity.java.PrimaryKeys;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private Connection connection;

    public DataBase() throws ClassNotFoundException, SQLException {
        Class.forName(DataConfig.DRIVER);
        String url = DataConfig.URL;
        String user = DataConfig.USER;
        String pass = DataConfig.PWD;
        connection = DriverManager.getConnection(url, user, pass);
    }

    public DataBase(String driver, String url, String user, String pwd) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, pwd);
    }

    /**
     * 获取数据表信息---- 列表
     */
    public List<JavaData> getTableList() throws SQLException {
        ArrayList<JavaData> javaDataArrayList = new ArrayList<JavaData>();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(connection.getCatalog(), "%", "%", null);
        while (tables.next()) {
            //数据表名
            String table_name = tables.getString("TABLE_NAME");
            if (null != table_name && !table_name.equals("")) {
                JavaData javaData = new JavaData();
                javaData.setDatabase(connection.getCatalog());
                javaData.setTable(table_name);
                javaData.setTableNote(getTableNotes(table_name));
                javaData.setTableType(tables.getString("TABLE_TYPE"));
                List<JavaColumn> javaColumnList = getColumnList(table_name);
                javaData.setJavaColumns(javaColumnList);//字段
                if (javaColumnList != null && javaColumnList.size() > 0) {
                    for (int i = 0; i < javaColumnList.size(); i++) {
                        JavaColumn javaColumn = javaColumnList.get(i);
                        if (javaColumn.getPrimary()) {
                            javaData.setPrimarykey(javaColumn.getName());
                            javaData.setPrimarykeyDbType(javaColumn.getDbType());
                            javaData.setPrimarykeyJavaType(javaColumn.getJavaType());
                            javaData.setPrimarykeyNote(javaColumn.getNote());
                            continue;
                        }
                    }
                }
                javaDataArrayList.add(javaData);
            }
        }
        return javaDataArrayList;
    }


    /**
     * 获取表字段信息----- 列表
     *
     * @param tableName 数据表名
     * @throws SQLException
     */
    public List<JavaColumn> getColumnList(String tableName) throws SQLException {
        ArrayList<JavaColumn> javaColumnList = new ArrayList<JavaColumn>();
        DatabaseMetaData metaData = connection.getMetaData();
        //==== 获取主键
        List<PrimaryKeys> getPrimaryKeys = getPrimaryKeys(tableName);
        //==== 获取外键
        List<ForeignKey> getForeignKeys = getForeignKeys(tableName);
        ResultSet columns = metaData.getColumns(connection.getCatalog(), "%", tableName, "%");
        while (columns.next()) {
            JavaColumn javaColumn = new JavaColumn();
            //=================
            javaColumn.setName(columns.getString("COLUMN_NAME"));//字段名
            javaColumn.setNote(columns.getString("REMARKS"));//注释
            javaColumn.setLenth(columns.getInt("COLUMN_SIZE"));//长度
            String type = columns.getString("TYPE_NAME");
            javaColumn.setDbType(type);
            javaColumn.setJavaType(TypeTools.dbType2JavaType(type));
            javaColumn.setAutoIncrement(columns.getString("IS_AUTOINCREMENT").equals("YES"));//是否自增加
            javaColumn.setDefaultValue(columns.getString("COLUMN_DEF"));//默认值
            for (int i = 0; i < getPrimaryKeys.size(); i++) {
                if (javaColumn.getName().equals(getPrimaryKeys.get(i).getColumnName())) {
                    javaColumn.setPrimary(true);
                }
            }
            for (int j = 0; j < getForeignKeys.size(); j++) {
                if (javaColumn.getName().equals(getForeignKeys.get(j).getFkColumnName())) {
                    javaColumn.setFktable(true);
                    javaColumn.setPkTableName(getForeignKeys.get(j).getPkTableName());
                    javaColumn.setPkTableColumn(getForeignKeys.get(j).getPkColumnName());
                    javaColumn.setPkTableNote(getForeignKeys.get(j).getPkTableNote());
                }
            }
            javaColumnList.add(javaColumn);
        }
        return javaColumnList;
    }


    /**
     * 获取主键列表
     *
     * @param tableName 数据表名
     * @throws SQLException
     */
    public List<PrimaryKeys> getPrimaryKeys(String tableName) throws SQLException {
        List<PrimaryKeys> list = new ArrayList<PrimaryKeys>();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet primaryKeys = metaData.getPrimaryKeys(connection.getCatalog(), "%", tableName);
        while (primaryKeys.next()) {
            PrimaryKeys primaryKeysList = new PrimaryKeys();
            primaryKeysList.setTableName(primaryKeys.getString("TABLE_NAME"));
            primaryKeysList.setColumnName(primaryKeys.getString("COLUMN_NAME"));
            primaryKeysList.setPrimarykeyName(primaryKeys.getString("PK_NAME"));
            list.add(primaryKeysList);
        }
        return list;
    }


    /**
     * 获取外键列表
     *
     * @param tableName 数据表名
     * @throws SQLException
     */
    public List<ForeignKey> getForeignKeys(String tableName) throws SQLException {
        List<ForeignKey> list = new ArrayList<ForeignKey>();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet foreignKeyResultSet = metaData.getImportedKeys(connection.getCatalog(), "%", tableName);
        while (foreignKeyResultSet.next()) {
            ForeignKey foreignKey = new ForeignKey();
            String pkTableName = foreignKeyResultSet.getString("PKTABLE_NAME");
            foreignKey.setPkTableName(pkTableName);
            foreignKey.setPkColumnName(foreignKeyResultSet.getString("PKCOLUMN_NAME"));
            foreignKey.setPkTableNote(getTableNotes(pkTableName));
            String fkTableName = foreignKeyResultSet.getString("FKTABLE_NAME");
            foreignKey.setFkTableName(fkTableName);
            foreignKey.setFkColumnName(foreignKeyResultSet.getString("FKCOLUMN_NAME"));
            foreignKey.setFkTableNote(getTableNotes(fkTableName));
            list.add(foreignKey);
        }
        return list;
    }


    /**
     * 获取表的注释
     *
     * @param tableName
     * @return
     */
    public String getTableNotes(String tableName) throws SQLException {
        String notes = null;
        ResultSet rsBiaoGe = connection.prepareStatement("show table status where Name = '" + tableName + "'").executeQuery();
        if (rsBiaoGe != null && rsBiaoGe.next()) {
            notes = rsBiaoGe.getString("Comment");
        }
        return notes;
    }


}















