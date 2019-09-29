package cn.cyyaw.util.buildcode.entity.java;

import lombok.Data;

/**
 * 外键
 */
@Data
public class ForeignKey {
    private String pkTableName;   //表名
    private String pkColumnName;  //指向的列
}
