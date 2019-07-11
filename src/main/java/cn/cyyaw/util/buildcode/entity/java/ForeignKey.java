package cn.cyyaw.util.buildcode.entity.java;

/**
 * 外键
 */
public class ForeignKey {

    /**
     * 外键指向表名
     */
    private String pkTableName;

    /**
     * 外键指向列名称
     */
    private String pkColumnName;

    /**
     * 外键指向表注释
     */
    private String pkTableNote;

    /**
     * 外键表名称
     */
    private String fkTableName;

    /**
     * 外键表列名
     */
    private String fkColumnName;

    /**
     * 外键表注释
     */
    private String fkTableNote;

    public void setPkTableName(String pkTableName) {
        this.pkTableName = pkTableName;
    }

    public void setPkColumnName(String pkColumnName) {
        this.pkColumnName = pkColumnName;
    }

    public void setPkTableNote(String pkTableNote) {
        this.pkTableNote = pkTableNote;
    }

    public void setFkTableName(String fkTableName) {
        this.fkTableName = fkTableName;
    }

    public void setFkColumnName(String fkColumnName) {
        this.fkColumnName = fkColumnName;
    }

    public void setFkTableNote(String fkTableNote) {
        this.fkTableNote = fkTableNote;
    }

    public String getPkTableName() {
        return pkTableName;
    }

    public String getPkColumnName() {
        return pkColumnName;
    }

    public String getPkTableNote() {
        return pkTableNote;
    }

    public String getFkTableName() {
        return fkTableName;
    }

    public String getFkColumnName() {
        return fkColumnName;
    }

    public String getFkTableNote() {
        return fkTableNote;
    }
}
