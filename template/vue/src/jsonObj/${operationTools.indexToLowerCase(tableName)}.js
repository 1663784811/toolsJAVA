import {EXPLAINFIELD,render,filterRemote} from "@/assets/common/js/global.js";

let json ={
    "title": "${tableNote}",
    "fieldList": [
        <#list vueJsons as column>
<#if column_index != 0 >,</#if>{
<#if column.type == 'selection'>
    width:60,
</#if>
    key:"${column.key!}",
    title:"${column.title!}",
<#if column.type != 'html'>
    type:"${column.type!}",
</#if>
    length:"${(column.length!255)?c}",
<#if column.isRequire>
    isRequire:${column.isRequire?string('true','false')},
</#if>
<#if (!(column.regStr)??)>
    regStr:"${column.regStr!}",
</#if>
<#if (!(column.message)??)>
    message:"${column.message!}",
</#if>
<#if (!(column.controlType)??)>
    controlType:"${column.controlType!}",
</#if>
<#if column.javaType == 'integer'>
    max:"${column.max!}",
    min:"${column.min!}",
</#if>
<#if column.format == 'integer'>
    format:"${column.format!}",
</#if>
<#if column.isShowColumn>
    isShowColumn:${column.isShowColumn?string('true','false')},
</#if>
<#if column.isWhere>
    isWhere:${column.isWhere?string('true','false')},
</#if>
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
