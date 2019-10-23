package ${basePackage}.service.impl;

import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import ${basePackage}.service.${__Table__}Service;
import ${basePackage}.table.entity.${__Table__};
import ${basePackage}.table.dao.${__Table__}Dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
<#list javaColumns as column>
<#if column.isFk>
import java.util.List;<#break>
</#if>
</#list>

@Service
@Transactional
@Slf4j
public class ${__Table__}ServiceImpl extends BaseService<${__Table__}, ${__pkJava__}> implements ${__Table__}Service {

    @Autowired
    private ${__Table__}Dao ${__table__}Dao;

    @Override
    public BaseDao getBaseDao() {
        return ${__table__}Dao;
    }

<#list javaColumns as column>
<#if column.isFk>
    /**
     * 外键查询
     */
    @Override
    public List<${__Table__}> fk${__Table__}Find${operationTools.indexToUpperCase(column.pkTableName)}(${column.javaType} ${operationTools.indexToLowerCase(column.pkTableColumn)}){
        return ${__table__}Dao.fk${__Table__}Find${operationTools.indexToUpperCase(column.pkTableName)}(${operationTools.indexToLowerCase(column.pkTableColumn)});
    }
</#if>
</#list>
}

