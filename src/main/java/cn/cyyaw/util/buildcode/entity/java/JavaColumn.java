package cn.cyyaw.util.buildcode.entity.java;

/**
 * 数据表字段
 */
public class JavaColumn {

    /**
     * 字段名
     */
    private String name;

    /**
     * 注释
     */
    private String note;
    /**
     * 长度
     */
    private Integer lenth;

    /**
     * 数据库类型
     */
    private String dbType;

    /**
     * java 类型
     */
    private String javaType;

    /**
     * 是否自增加
     */
    private Boolean isAutoIncrement;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 是否是唯一
     */
    private Boolean isUnique = false;

    /**
     * 是否可以为null
     */
    private Boolean isNull = true;

    /**
     * 是否是主键
     */
    private Boolean isPrimary = false;

    /**
     * 是否是外键
     */
    private Boolean isFktable = false;

    /**
     * 外键指向的表
     */
    private String pkTableName;

    /**
     * 外键指向的列
     */
    private String pkTableColumn;

    /**
     * 外键指向的表注释
     */
    private String pkTableNote;

    public void setNull(Boolean aNull) {
        isNull = aNull;
    }

    public Boolean getNull() {
        return isNull;
    }

    public void setUnique(Boolean unique) {
        isUnique = unique;
    }

    public Boolean getUnique() {
        return isUnique;
    }

    public void setPkTableNote(String pkTableNote) {
        this.pkTableNote = pkTableNote;
    }

    public String getPkTableNote() {
        return pkTableNote;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }

    public void setPkTableName(String pkTableName) {
        this.pkTableName = pkTableName;
    }

    public void setPkTableColumn(String pkTableColumn) {
        this.pkTableColumn = pkTableColumn;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public String getPkTableName() {
        return pkTableName;
    }

    public String getPkTableColumn() {
        return pkTableColumn;
    }

    public void setAutoIncrement(Boolean autoIncrement) {
        isAutoIncrement = autoIncrement;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public void setFktable(Boolean fktable) {
        isFktable = fktable;
    }


    public Boolean getAutoIncrement() {
        return isAutoIncrement;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public Boolean getFktable() {
        return isFktable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLenth() {
        return lenth;
    }

    public void setLenth(Integer lenth) {
        this.lenth = lenth;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getDbType() {
        return dbType;
    }

    public String getJavaType() {
        return javaType;
    }
}
