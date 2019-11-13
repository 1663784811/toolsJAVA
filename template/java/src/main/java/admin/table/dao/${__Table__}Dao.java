package ${basePackage}.table.dao;

import cn.cyyaw.jpa.BaseDao;
import ${basePackage}.table.entity.${__Table__};

<#list javaColumns as column>
<#if column.isFk>
import java.util.List;
import org.springframework.data.jpa.repository.Query;<#break>
</#if>
</#list>

public interface ${__Table__}Dao extends BaseDao<${__Table__}, ${__pkJava__}> {

<#list javaColumns as column>
<#if column.isFk>
    /**
     * 外键查询
     */
    @Query("select model from ${__table__} model where model.${operationTools.indexToLowerCase(column.pkTableName)} in (select m.${operationTools.indexToLowerCase(column.pkTableColumn)} from ${operationTools.indexToUpperCase(column.pkTableName)} m where m.${operationTools.indexToLowerCase(column.pkTableColumn)} = ?1 )")
    List<${__table__}> fk${__table__}Find${operationTools.indexToUpperCase(column.pkTableName)}(${column.javaType} ${operationTools.indexToLowerCase(column.pkTableColumn)});
</#if>
</#list>


}
