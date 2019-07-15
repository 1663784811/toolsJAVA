package ${basePackage}.admin.service;

import ${basePackage}.admin.table.${operationTools.allToLowerCase(tableName)}.${operationTools.indexToUpperCase(tableName)};
import cn.cyyaw.jpa.BaseTableService;

import java.util.List;

public interface ${operationTools.indexToUpperCase(tableName)}Service extends BaseTableService<${operationTools.indexToUpperCase(tableName)}, ${primarykeyJavaType}> {

<#list javaColumns as column>
<#if column.fktable>
    /**
     * 外键查询
     */
    List<${operationTools.indexToUpperCase(tableName)}> fk${operationTools.indexToUpperCase(tableName)}Find${operationTools.indexToUpperCase(column.pkTableName)}(${column.javaType} ${operationTools.indexToLowerCase(column.pkTableColumn)});
</#if>
</#list>


}
