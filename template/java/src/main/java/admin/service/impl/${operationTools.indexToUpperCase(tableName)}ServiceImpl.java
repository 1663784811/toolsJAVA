package ${basePackage}.admin.service.impl;

import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import ${basePackage}.admin.service.${operationTools.indexToUpperCase(tableName)}Service;
import ${basePackage}.admin.table.${operationTools.allToLowerCase(tableName)}.${operationTools.indexToUpperCase(tableName)};
import ${basePackage}.admin.table.${operationTools.allToLowerCase(tableName)}.${operationTools.indexToUpperCase(tableName)}Dao;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@Log4j
public class ${operationTools.indexToUpperCase(tableName)}ServiceImpl extends BaseService<${operationTools.indexToUpperCase(tableName)}, ${primarykeyJavaType}> implements ${operationTools.indexToUpperCase(tableName)}Service {

    @Autowired
    private ${operationTools.indexToUpperCase(tableName)}Dao ${operationTools.indexToLowerCase(tableName)}Dao;

    @Override
    public BaseDao getBaseDao() {
        return ${operationTools.indexToLowerCase(tableName)}Dao;
    }

<#list javaColumns as column>
<#if column.fktable>
    /**
     * 外键查询
     */
    @Override
    public List<${operationTools.indexToUpperCase(tableName)}> fk${operationTools.indexToUpperCase(tableName)}Find${operationTools.indexToUpperCase(column.pkTableName)}(${column.javaType} ${operationTools.indexToLowerCase(column.pkTableColumn)}){
        return ${operationTools.indexToLowerCase(tableName)}Dao.fk${operationTools.indexToUpperCase(tableName)}Find${operationTools.indexToUpperCase(column.pkTableName)}(${operationTools.indexToLowerCase(column.pkTableColumn)});
    }
</#if>
</#list>
}

