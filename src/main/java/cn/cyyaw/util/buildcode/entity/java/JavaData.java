package cn.cyyaw.util.buildcode.entity.java;

import lombok.Data;

import java.util.List;

@Data
public class JavaData {

    /**
     * 数据库
     */
    private String database;

    /**
     * 数据表
     */
    private String table;

    /**
     * 表注释
     */
    private String tableNote;

    /**
     * 类型
     */
    private String tableType;

    /**
     * 主键
     */
    private String primarykey;

    /**
     * 主键注释
     */
    private String primarykeyNote;

    /**
     * 主键类型
     */
    private String primarykeyDbType;

    /**
     * 主键类型
     */
    private String primarykeyJavaType;
    /**
     * 字段列表
     */
    private List<JavaColumn> javaColumns;
}
