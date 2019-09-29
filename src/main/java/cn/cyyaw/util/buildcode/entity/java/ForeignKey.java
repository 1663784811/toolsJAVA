package cn.cyyaw.util.buildcode.entity.java;

import lombok.Data;

/**
 * 外键
 */
@Data
public class ForeignKey {
    private String pkTableName;  //外键指向表名
    private String pkColumnName;  //外键指向列名称
    private String pkTableNote;  // 外键指向表注释
    private String fkTableName;  //外键表名称
    private String fkColumnName;  //外键表列名
    private String fkTableNote;  //外键表注释
}
