package ${basePackage}.admin.table.${operationTools.allToLowerCase(tableName)};

import cn.cyyaw.jpa.BaseDao;
import ${basePackage}.admin.table.entity.${operationTools.indexToUpperCase(tableName)};

<#if javaColumns?? && (javaColumns?size > 0)   >
import java.util.List;
import org.springframework.data.jpa.repository.Query;
</#if>

public interface ${operationTools.indexToUpperCase(tableName)}Dao extends BaseDao<${operationTools.indexToUpperCase(tableName)}, ${primarykeyJavaType}> {

<#list javaColumns as column>
<#if column.fktable>
    /**
     * 外键查询
     */
    @Query("select model from ${operationTools.indexToUpperCase(tableName)} model where model.${operationTools.indexToLowerCase(column.pkTableName)} in (select m.${operationTools.indexToLowerCase(column.pkTableColumn)} from ${operationTools.indexToUpperCase(column.pkTableName)} m where m.${operationTools.indexToLowerCase(column.pkTableColumn)} = ?1 )")
    List<${operationTools.indexToUpperCase(tableName)}> fk${operationTools.indexToUpperCase(tableName)}Find${operationTools.indexToUpperCase(column.pkTableName)}(${column.javaType} ${operationTools.indexToLowerCase(column.pkTableColumn)});
</#if>
</#list>


}
