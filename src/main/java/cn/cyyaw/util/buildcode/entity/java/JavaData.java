package cn.cyyaw.util.buildcode.entity.java;

import java.util.List;

public class JavaData {

    /**
     * 数据库
     */
    private String database;

    /**
     * 数据表
     */
    private String table;

    /**
     * 表注释
     */
    private String tableNote;

    /**
     * 类型
     */
    private String tableType;

    /**
     * 主键
     */
    private String primarykey;

    /**
     * 主键注释
     */
    private String primarykeyNote;

    /**
     * 主键类型
     */
    private String primarykeyDbType;

    /**
     * 主键类型
     */
    private String primarykeyJavaType;
    /**
     * 字段列表
     */
    private List<JavaColumn> javaColumns;


    public void setPrimarykeyDbType(String primarykeyDbType) {
        this.primarykeyDbType = primarykeyDbType;
    }

    public void setPrimarykeyJavaType(String primarykeyJavaType) {
        this.primarykeyJavaType = primarykeyJavaType;
    }

    public String getPrimarykeyDbType() {
        return primarykeyDbType;
    }

    public String getPrimarykeyJavaType() {
        return primarykeyJavaType;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getDatabase() {
        return database;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public void setTableNote(String tableNote) {
        this.tableNote = tableNote;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public void setPrimarykey(String primarykey) {
        this.primarykey = primarykey;
    }

    public void setPrimarykeyNote(String primarykeyNote) {
        this.primarykeyNote = primarykeyNote;
    }


    public void setJavaColumns(List<JavaColumn> javaColumns) {
        this.javaColumns = javaColumns;
    }

    public String getTable() {
        return table;
    }

    public String getTableNote() {
        return tableNote;
    }

    public String getTableType() {
        return tableType;
    }

    public String getPrimarykey() {
        return primarykey;
    }

    public String getPrimarykeyNote() {
        return primarykeyNote;
    }


    public List<JavaColumn> getJavaColumns() {
        return javaColumns;
    }
}
