import {AJAXGET, AJAXPOST} from "../webinfo";
import {_BaseUrl as baseurl} from "../config";

/**
 * 获取所有表格 ==>所有
 */
export const API_findAll${__Table__} = (parameter = {}) => AJAXGET(baseurl + "/admin/findAll${__Table__}", parameter);


/**
 * 获取数据 ==>分页
 */
export const API_findPage${__Table__} = (parameter) => AJAXGET(baseurl + "/admin/findPage${__Table__}", parameter);

/**
 *  根据ID查找
 */
export const API_findId${__Table__} = (parameter) => AJAXGET(baseurl + "/admin/findId${__Table__}", parameter);

/**
 * 添加 或 更新
 */
export const API_save${__Table__} = (parameter) => AJAXPOST(baseurl + "/admin/save${__Table__}", parameter);

/**
 * 删除
 */
export const API_del${__Table__} = (parameter) => AJAXPOST(baseurl + "/admin/del${__Table__}", parameter);


<#list javaColumns as column>
    <#if column.isFk>
/**
 * 外键
 */
export const API_fk${__Table__}Find${operationTools.indexToUpperCase(column.pkTableName)} = (parameter) => AJAXPOST(baseurl + "/admin/fk${__Table__}Find${operationTools.indexToUpperCase(column.pkTableName)}", parameter);
    </#if>
</#list>