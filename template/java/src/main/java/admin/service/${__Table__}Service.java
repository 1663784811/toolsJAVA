package ${basePackage}.service;

import ${basePackage}.table.entity.${__table__};
import cn.cyyaw.jpa.BaseTableService;
<#list javaColumns as column>
<#if column.isFk>
import java.util.List;<#break>
</#if>
</#list>

public interface ${__table__}Service extends BaseTableService<${__table__}, ${__pkJava__}> {

<#list javaColumns as column>
<#if column.isFk>
    /**
     * 外键查询
     */
    List<${__table__}> fk${__table__}Find${operationTools.indexToUpperCase(column.pkTableName)}(${column.javaType} ${operationTools.indexToLowerCase(column.pkTableColumn)});
</#if>
</#list>


}
