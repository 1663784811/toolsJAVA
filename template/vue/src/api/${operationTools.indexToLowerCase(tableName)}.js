import {AJAXGET, AJAXPOST} from "../webinfo";
import {_BaseUrl as baseurl} from "../config";

/**
 * 获取所有表格 ==>所有
 */
export const API_findAll${operationTools.indexToUpperCase(tableName)} = (parameter = {}) => AJAXGET(baseurl + "/admin/findAll${operationTools.indexToUpperCase(tableName)}", parameter);


/**
 * 获取数据 ==>分页
 */
export const API_findPage${operationTools.indexToUpperCase(tableName)} = (parameter) => AJAXGET(baseurl + "/admin/findPage${operationTools.indexToUpperCase(tableName)}", parameter);

/**
 *  根据ID查找
 */
export const API_findId${operationTools.indexToUpperCase(tableName)} = (parameter) => AJAXGET(baseurl + "/admin/findId${operationTools.indexToUpperCase(tableName)}", parameter);

/**
 * 添加 或 更新
 */
export const API_save${operationTools.indexToUpperCase(tableName)} = (parameter) => AJAXPOST(baseurl + "/admin/save${operationTools.indexToUpperCase(tableName)}", parameter);

/**
 * 删除
 */
export const API_del${operationTools.indexToUpperCase(tableName)} = (parameter) => AJAXPOST(baseurl + "/admin/del${operationTools.indexToUpperCase(tableName)}", parameter);

