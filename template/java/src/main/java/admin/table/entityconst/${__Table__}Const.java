package ${basePackage}.table.entityconst;

/**
 * 表常量
 * 类命名：表名Const
 */
public class ${__Table__}Const {

    //允许查询列表
    public final static String selectColumnArr[] = {<#list javaColumns as column ><#if !column.isFk>"${operationTools.allToLowerCase(column.columnName)}"<#if column_index+1 lt javaColumns?size >,</#if></#if></#list>};
    //允许查询条件
    public final static String selectWhereArr[] = {<#list javaColumns as column ><#if !column.isFk>"${operationTools.allToLowerCase(column.columnName)}"<#if column_index+1 lt javaColumns?size >,</#if></#if></#list>};
    //允许更新字段
    public final static String updateColumnArr[] = {<#list javaColumns as column >"${operationTools.allToLowerCase(column.columnName)}"<#if column_index+1 lt javaColumns?size >,</#if></#list>};
    //允许删除条件
    public final static String deleteColumnArr[] = {<#list javaColumns as column >"${operationTools.allToLowerCase(column.columnName)}"<#if column_index+1 lt javaColumns?size >,</#if></#list>};
    //允许添加字段
    public final static String addColumnArr[] = {<#list javaColumns as column >"${operationTools.allToLowerCase(column.columnName)}"<#if column_index+1 lt javaColumns?size >,</#if></#list>};


    //过滤字段==  查询列表
    public final static String filterselectColumnArr[] = {};
    //过滤字段==  查询条件
    public final static String filterselectWhereArr[] = {};
    //过滤字段==  更新字段
    public final static String filterupdateColumnArr[] = {};
    //过滤字段==  删除条件
    public final static String filterdeleteColumnArr[] = {};
    //过滤字段==  添加字段
    public final static String filteraddColumnArr[] = {};


}
