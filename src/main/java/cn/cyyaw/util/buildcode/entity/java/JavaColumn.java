package cn.cyyaw.util.buildcode.entity.java;

import lombok.Data;

/**
 * 数据表字段
 */
@Data
public class JavaColumn {
    private String name;  //字段名
    private String dbType; //数据库类型
    private Integer length;  //长度
    private String javaType; //java 类型
    private Boolean isAutoIncrement; // 是否自增加
    private String defaultValue; //默认值
    private Boolean isUnique = false; //是否是唯一
    private Boolean isNull = true; //是否可以为null
    private Boolean isPrimary = false; //是否是主键
    private Boolean isFktable = false; //是否是外键
    private String pkTableName; //外键指向的表
    private String pkTableColumn; // 外键指向的列
    private String pkTableNote; //外键指向的表注释
    private String note; //注释
}
