package cn.cyyaw.util.buildcode.entity.java;

import lombok.Data;

/**
 * 数据表字段
 */
@Data
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
}
