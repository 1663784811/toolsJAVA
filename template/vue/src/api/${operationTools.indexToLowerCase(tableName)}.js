import {AJAXGET, AJAXPOST} from "@/assets/common/js/global";

const baseurl = "http://localhost:8080";


/**
 * 获取所有表格
 */
export const API_findAll${operationTools.indexToUpperCase(tableName)} = (parameter = {}) => AJAXGET(baseurl + "/admin/findAll${operationTools.indexToUpperCase(tableName)}", parameter);


/**
 * 获取表格数据
 */
export const API_findPage${operationTools.indexToUpperCase(tableName)} = (parameter) => AJAXGET(baseurl + "/admin/findPage${operationTools.indexToUpperCase(tableName)}", parameter);

/**
 *  查找
 */
export const API_findId${operationTools.indexToUpperCase(tableName)} = (parameter) => AJAXGET(baseurl + "/admin/findId${operationTools.indexToUpperCase(tableName)}", parameter);


/**
 * 添加
 */
export const API_add${operationTools.indexToUpperCase(tableName)} = (parameter) => AJAXPOST(baseurl + "/admin/add${operationTools.indexToUpperCase(tableName)}", parameter);


/**
 *更新
 */
export const API_update${operationTools.indexToUpperCase(tableName)} = (parameter) => AJAXPOST(baseurl + "/admin/update${operationTools.indexToUpperCase(tableName)}", parameter);


/**
 * 删除
 */
export const API_del${operationTools.indexToUpperCase(tableName)} = (parameter) => AJAXPOST(baseurl + "/admin/del${operationTools.indexToUpperCase(tableName)}", parameter);

