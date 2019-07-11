package ${basePackage}.admin.service.impl;

import ${basePackage}.admin.service.${operationTools.indexToUpperCase(tableName)}Service;
import ${basePackage}.admin.table.${operationTools.allToLowerCase(tableName)}.${operationTools.indexToUpperCase(tableName)};
import ${basePackage}.admin.table.${operationTools.allToLowerCase(tableName)}.${operationTools.indexToUpperCase(tableName)}Constants;
import ${basePackage}.admin.table.${operationTools.allToLowerCase(tableName)}.${operationTools.indexToUpperCase(tableName)}Dao;
import ${basePackage}.common.tools.JpaUtils;
import ${basePackage}.common.tools.WhySpecification;
import ${basePackage}.system.jpa.model.SelectModel;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;


@Service
@Transactional
public class ${operationTools.indexToUpperCase(tableName)}ServiceImpl implements ${operationTools.indexToUpperCase(tableName)}Service {

    //日志
    private static final Logger LOGGER = LoggerFactory.getLogger(${operationTools.indexToUpperCase(tableName)}ServiceImpl.class);

    @Autowired
    private ${operationTools.indexToUpperCase(tableName)}Dao ${operationTools.indexToLowerCase(tableName)}Dao;

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<${operationTools.indexToUpperCase(tableName)}> findAll(String jsonStr, SelectModel selectModel) {
        Sort sort = JpaUtils.getSort(selectModel);
        if (null != sort) {
            return ${operationTools.indexToLowerCase(tableName)}Dao.findAll(new WhySpecification<${operationTools.indexToUpperCase(tableName)}>(jsonStr, ${operationTools.indexToUpperCase(tableName)}Constants.selectWhereArr), sort);
        } else {
            return ${operationTools.indexToLowerCase(tableName)}Dao.findAll(new WhySpecification<${operationTools.indexToUpperCase(tableName)}>(jsonStr, ${operationTools.indexToUpperCase(tableName)}Constants.selectWhereArr));
        }
    }


    /**
     * 分页条件查询
     *
     * @return
     */
    @Override
    public Page<${operationTools.indexToUpperCase(tableName)}> findPage(String jsonStr, PageRequest pageRequest) {
        Page<${operationTools.indexToUpperCase(tableName)}> pageList = ${operationTools.indexToLowerCase(tableName)}Dao.findAll(new WhySpecification<${operationTools.indexToUpperCase(tableName)}>(jsonStr, ${operationTools.indexToUpperCase(tableName)}Constants.selectWhereArr), pageRequest);
        return pageList;
    }


    /**
     * 根据ID查询
     *
     * @return
     */
    @Override
    public ${operationTools.indexToUpperCase(tableName)} findId(${primarykeyJavaType} ${operationTools.indexToLowerCase(primarykey)}) {
        return ${operationTools.indexToLowerCase(tableName)}Dao.findByid(${operationTools.indexToLowerCase(primarykey)});
    }

    /**
     * 添加
     */
    @Override
    public ${operationTools.indexToUpperCase(tableName)} add(${operationTools.indexToUpperCase(tableName)} ${operationTools.indexToLowerCase(tableName)}) {
        //  1. =======添加初始化参数或默认参数
        //  2. =======实现添加并返回
        ${operationTools.indexToUpperCase(tableName)} ${operationTools.indexToLowerCase(tableName)}1 = ${operationTools.indexToLowerCase(tableName)}Dao.save(${operationTools.indexToLowerCase(tableName)});
        Assert.notNull(${operationTools.indexToLowerCase(tableName)}1, "添加失败！");
        return ${operationTools.indexToLowerCase(tableName)}1;
    }

    /**
     * 修改
     */
    @Override
    public ${operationTools.indexToUpperCase(tableName)} update(${operationTools.indexToUpperCase(tableName)} ${operationTools.indexToLowerCase(tableName)}) {
        //  1. =======  查询一次
        ${operationTools.indexToUpperCase(tableName)} ${operationTools.indexToLowerCase(tableName)}1 = ${operationTools.indexToLowerCase(tableName)}Dao.findByid(${operationTools.indexToLowerCase(tableName)}.getId());
        Assert.notNull(${operationTools.indexToLowerCase(tableName)}1, "没有查询到记录！");
        //  2. =======  过滤数据
        //  3. =======  实现修改并返回
        return ${operationTools.indexToLowerCase(tableName)}Dao.save(${operationTools.indexToLowerCase(tableName)});
    }

    /**
     *  删除数据
     */
    @Override
    public void del(${primarykeyJavaType}[] ${operationTools.indexToLowerCase(primarykey)}Arr) {
        List<${operationTools.indexToUpperCase(tableName)}> ${operationTools.indexToLowerCase(tableName)}IsIn = ${operationTools.indexToLowerCase(tableName)}Dao.findByidIsIn(${operationTools.indexToLowerCase(primarykey)}Arr);
        ${operationTools.indexToLowerCase(tableName)}Dao.deleteInBatch(${operationTools.indexToLowerCase(tableName)}IsIn);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("========  删除数据 ======");
            LOGGER.info(${operationTools.indexToLowerCase(tableName)}IsIn.toString());
            LOGGER.info("========  删除数据 ======");
        }
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

