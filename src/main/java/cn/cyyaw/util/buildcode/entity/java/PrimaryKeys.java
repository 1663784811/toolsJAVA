package cn.cyyaw.util.buildcode.entity.java;

import lombok.Data;

/**
 * 主键
 */
@Data
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
}
