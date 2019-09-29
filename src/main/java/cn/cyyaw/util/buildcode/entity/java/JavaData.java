package cn.cyyaw.util.buildcode.entity.java;

import lombok.Data;

import java.util.List;

@Data
public class JavaData {
    private String database;                //数据库
    private String table;                   //数据表
    private String tableNote;               //表注释
    private String tableType;               //类型

//    private String primarykey;              //主键
//    private String primarykeyNote;          //主键注释
//    private String primarykeyDbType;        //主键类型
//    private String primarykeyJavaType;      //主键类型
    private List<JavaColumn> javaColumns;   //字段列表
}
