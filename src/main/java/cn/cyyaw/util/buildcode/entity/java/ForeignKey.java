package cn.cyyaw.util.buildcode.entity.java;

import lombok.Data;

/**
 * 外键
 */
@Data
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
}
