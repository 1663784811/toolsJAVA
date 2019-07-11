package cn.cyyaw.util.buildcode.entity.java;

/**
 * 主键
 */
public class PrimaryKeys {

    /**
     * 表名
     */
    private String tableName;
    /**
     * 指向的主键列
     */
    private String columnName;

    /**
     * 主键名
     */
    private String primarykeyName;

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void setPrimarykeyName(String primarykeyName) {
        this.primarykeyName = primarykeyName;
    }

    public String getTableName() {
        return tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getPrimarykeyName() {
        return primarykeyName;
    }
}
