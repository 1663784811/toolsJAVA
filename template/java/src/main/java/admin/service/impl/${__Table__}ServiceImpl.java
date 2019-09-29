package ${basePackage}.service.impl;

import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import ${basePackage}.service.${__Table__}Service;
import ${basePackage}.table.entity.${__Table__};
import ${basePackage}.table.dao.${__Table__}Dao;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
<#list javaColumns as column>
<#if column.isFktable>
import java.util.List;<#break>
</#if>
</#list>

@Service
@Transactional
@Log4j
public class ${__Table__}ServiceImpl extends BaseService<${__Table__}, ${primarykeyJavaType}> implements ${__Table__}Service {

    @Autowired
    private ${__Table__}Dao ${operationTools.indexToLowerCase(tableName)}Dao;

    @Override
    public BaseDao getBaseDao() {
        return ${operationTools.indexToLowerCase(tableName)}Dao;
    }

<#list javaColumns as column>
<#if column.isFktable>
    /**
     * 外键查询
     */
    @Override
    public List<${__Table__}> fk${__Table__}Find${operationTools.indexToUpperCase(column.pkTableName)}(${column.javaType} ${operationTools.indexToLowerCase(column.pkTableColumn)}){
        return ${operationTools.indexToLowerCase(tableName)}Dao.fk${__Table__}Find${operationTools.indexToUpperCase(column.pkTableName)}(${operationTools.indexToLowerCase(column.pkTableColumn)});
    }
</#if>
</#list>
}

