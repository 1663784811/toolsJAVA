package ${basePackage}.admin.service;

import ${basePackage}.admin.table.entity.${operationTools.indexToUpperCase(tableName)};
import cn.cyyaw.jpa.BaseTableService;
<#list javaColumns as column>
<#if column.fktable>
import java.util.List;<#break>
</#if>
</#list>

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
