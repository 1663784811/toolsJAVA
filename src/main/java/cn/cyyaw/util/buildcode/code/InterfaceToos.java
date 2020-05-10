package cn.cyyaw.util.buildcode.code;

import cn.cyyaw.util.buildcode.entity.java.JavaColumn;
/**
 * 注解工具
 */
public class InterfaceToos {


    /**
     * 生成注解
     *
     * @param javaColumn
     * @return @Column(name = "address" , length = 45 , columnDefinition = "varchar(45)" )
     */
    public static String column(JavaColumn javaColumn) {
        String columnStr = "@Column(name=\"" + javaColumn.getColumnName() + "\""
                // + OperationTools.dbTypeLength(javaColumn)
                + OperationTools.dbTypeUnique(javaColumn)
                + OperationTools.dbTypeNull(javaColumn)
                + OperationTools.dbTypeEntity(javaColumn)
                + ")";
        return columnStr;
    }
}
