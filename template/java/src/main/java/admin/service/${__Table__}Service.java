package ${basePackage}.service;

import ${basePackage}.table.entity.${__Table__};
import cn.cyyaw.jpa.BaseTableService;
<#list javaColumns as column>
<#if column.isFk>
import java.util.List;<#break>
</#if>
</#list>

public interface ${__Table__}Service extends BaseTableService<${__Table__}, ${__pkJava__}> {

<#list javaColumns as column>
<#if column.isFk>
    /**
     * 外键查询
     */
    List<${__table__}> fk${__table__}Find${operationTools.indexToUpperCase(column.pkTableName)}(${column.javaType} ${operationTools.indexToLowerCase(column.pkTableColumn)});
</#if>
</#list>


}
