package cn.cyyaw.util.buildcode.entity.java;

import lombok.Data;

/**
 * 主键
 */
@Data
public class PrimaryKeys {
    private String tableName;  //表名
    private String columnName;  //指向的主键列
    private String primarykeyName;  //主键名
}
