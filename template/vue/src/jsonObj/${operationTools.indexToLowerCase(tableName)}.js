import {EXPLAINFIELD,render,filterRemote} from "@/assets/common/js/global.js";

let json ={
  "title": "${tableNote}",
  "fieldList": [
<#list vueJsons as column>
    <#if column_index != 0 >,</#if>{
      <#if column.type == 'selection'>
      width:"60",
      </#if>
      key:"${column.key!}",
      title:"${column.title!}",
      type:"${column.type!}",
      length:"${(column.length!255)?c}",
      isRequire:${column.isRequire?string('true','false')},
      regStr:"${column.regStr!}",
      message":"${column.message!}",
      controlType:"${column.controlType!}",
<#if column.javaType == 'integer'>
      max:"${column.max!}",
      min:"${column.min!}",
</#if>
      format:"${column.format!}",
      isShowColumn:${column.isShowColumn?string('true','false')},
      isWhere:${column.isWhere?string('true','false')},
      javaWhere:"${column.javaWhere!}",
      javaType:"${column.javaType!}"
    }
</#list>
  ]
};

//==== 解释json数据
const obj = EXPLAINFIELD(json);
//=====  标题
export const TitleJson = obj.title;
//===== 添加字段
export const addFieldDataJson = obj.addFieldData;
//===== 修改字段
export const updateFieldDataJson = obj.updateFieldData;
//=====  表头
export const TableHeaderJson = obj.TableHeader;
//=====  搜索字段
export const searchWhereJson = obj.searchWhere;




