package ${basePackage}.admin.controller;

import ${basePackage}.admin.service.${operationTools.indexToUpperCase(tableName)}Service;
import ${basePackage}.admin.table.${operationTools.allToLowerCase(tableName)}.${operationTools.indexToUpperCase(tableName)};
import ${basePackage}.admin.table.${operationTools.allToLowerCase(tableName)}.${operationTools.indexToUpperCase(tableName)}Constants;

import cn.cyyaw.jpa.BaseConstants;
import cn.cyyaw.util.entity.SelectModel;
import cn.cyyaw.util.tools.JpaUtils;
import cn.cyyaw.util.tools.ResponseUtils;
import cn.cyyaw.util.tools.WhyBeanUtils;

<#list javaColumns as column>
    <#if column.fktable>
import ${basePackage}.admin.table.${operationTools.allToLowerCase(column.pkTableName)}.${operationTools.indexToUpperCase(column.pkTableName)};
import ${basePackage}.admin.table.${operationTools.allToLowerCase(column.pkTableName)}.${operationTools.indexToUpperCase(column.pkTableName)}Constants;
    </#if>
</#list>

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *
 * =========================  声名 START
 * 所有实体类： 属性都是小写
 * =========================  声名 END
 *
 * jsonChaXun - 查询json
 *      例：like_string_cjjlYhmc 对应 (1)_(2)_(3)
 *      (1) - 本条件进行查询时与值的对比关系
 *          like : where [字符串] like '%YYYY%'
 *          likeleft : where [字符串] like 'YYYY%'
 *          likeright : where [字符串] like '%YYYY'
 *          equals : where [字符串/数/日期] = YYY
 *          notequals : where [字符串/数/日期] != YYY
 *          ge : where [数/日期] >= YYY
 *          gt : where [数/日期] > YYY
 *          le : where [数/日期] <= YYY
 *          lt : where [数/日期] < YYY
 *      (2) - 本条件字段的数据类型
 *          string integer float double date
 *      (3) - 本条件对应字段的实体类（不区分大小写）
 *
 *
 * 简单查询条件  and or
 * json 格式：  {like_string_adminid:"abcdefg",like_string_username:"WHY"}
 * 对应的sql 语句：　　where  adminid like '%abcdefg%'  and username like '%WHY%'
 *
 * 复杂查询条件
 * json 格式
 * {or:{like_string_adminid:"abcdefg",like_string_username:"WHY"},like_string_username:"WHY"}
 * 对应的sql 语句： where ( adminid like '%abcdefg%' and username like '%WHY%' ) or username like '%WHY%'
 *
 *  jsonStr格式：      {like_string_adminid:"abcdefg",like_string_username:"WHY"}    传参： http://localhost:8080/admin?jsonStr={like_string_adminid:"abcdefg",like_string_username:"WHY"}
 *  sort格式：         admin_desc,adminname_asc                                      传参： http://localhost:8080/admin?sort=admin_desc,adminname_asc
 *  page格式：         1                                                             传参： http://localhost:8080/admin?page=1
 *  size格式：         30                                                            传参： http://localhost:8080/admin?size=30
 *  id格式:            abcddeffdfd                                                   传参： http://localhost:8080/admin?id=abcddeffdfd
 *  对象格式:          对象                                                           传参： http://localhost:8080/admin? 对象
 *  id数组格式:                                                                       传参： http://localhost:8080/admin? id数组
 *
 * 1.  查询所有         findAll表名     有参      jsonStr    sort
 *     分页条件查询     findPage表名    有参       jsonStr    sort    page    size
 *     根据ID查询      findId表名       有参      id
 *
 * 2.  添加           add表名          有参       对象
 *
 * 3.  修改           update表名       有参       对象
 *
 * 4.  删除           del表名          有参       id数组
 *
 *  </pre>
 */
@Slf4j
@RequestMapping("/admin")
@RestController
public class ${operationTools.indexToUpperCase(tableName)}Controller {

    @Autowired
    private ${operationTools.indexToUpperCase(tableName)}Service ${operationTools.indexToLowerCase(tableName)}Service;

    /**
     * 表:${tableName} ===> 所有:带条件
     */
    @RequestMapping(value = "/findAll${operationTools.indexToUpperCase(tableName)}", method = RequestMethod.GET)
    public void findAll${operationTools.indexToUpperCase(tableName)}(HttpServletResponse response, String jsonStr, SelectModel selectModel) {
        List<${operationTools.indexToUpperCase(tableName)}> list = ${operationTools.indexToLowerCase(tableName)}Service.findAll(jsonStr, selectModel);
        ResponseUtils.responseJsonFilter(response, list,${operationTools.indexToUpperCase(tableName)}Constants.filterselectColumnArr);
    }

    /**
     * 分页条件查询
     */
    @RequestMapping(value = "/findPage${operationTools.indexToUpperCase(tableName)}", method = RequestMethod.GET)
    public void findPage${operationTools.indexToUpperCase(tableName)}(HttpServletResponse response,String jsonStr,  SelectModel selectModel) {
        PageRequest pageRequest = JpaUtils.getPageRequest(selectModel);
        Page<${operationTools.indexToUpperCase(tableName)}> page = ${operationTools.indexToLowerCase(tableName)}Service.findPage(jsonStr, pageRequest);
        ResponseUtils.responseJsonFilter(response, page,${operationTools.indexToUpperCase(tableName)}Constants.filterselectColumnArr);
    }

    /**
     * 根据ID查询
     */
    @RequestMapping(value = "/findId${operationTools.indexToUpperCase(tableName)}", method = RequestMethod.GET)
    public void findId${operationTools.indexToUpperCase(tableName)}(HttpServletResponse response,@RequestParam ${primarykeyJavaType} ${operationTools.indexToLowerCase(primarykey)}) {
        ${operationTools.indexToUpperCase(tableName)} obj = ${operationTools.indexToLowerCase(tableName)}Service.findId(${operationTools.indexToLowerCase(primarykey)});
        ResponseUtils.responseJsonFilter(response, obj,${operationTools.indexToUpperCase(tableName)}Constants.filterselectColumnArr);
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add${operationTools.indexToUpperCase(tableName)}", method = RequestMethod.POST)
    public void add${operationTools.indexToUpperCase(tableName)}(HttpServletResponse response,@RequestParam ${operationTools.indexToUpperCase(tableName)} ${operationTools.indexToLowerCase(tableName)}) {
        WhyBeanUtils.filterField(${operationTools.indexToLowerCase(tableName)}, ${operationTools.indexToUpperCase(tableName)}Constants.filteraddColumnArr);
        ${operationTools.indexToUpperCase(tableName)} obj = ${operationTools.indexToLowerCase(tableName)}Service.add(${operationTools.indexToLowerCase(tableName)});
        ResponseUtils.responseJsonFilter(response, obj,${operationTools.indexToUpperCase(tableName)}Constants.filterselectColumnArr);
    }


    /**
     * 修改
     */
    @RequestMapping(value = "/update${operationTools.indexToUpperCase(tableName)}", method = RequestMethod.POST)
    public void update${operationTools.indexToUpperCase(tableName)}(HttpServletResponse response,final ${operationTools.indexToUpperCase(tableName)} ${operationTools.indexToLowerCase(tableName)}) {
        WhyBeanUtils.filterField(${operationTools.indexToLowerCase(tableName)}, ${operationTools.indexToUpperCase(tableName)}Constants.filterupdateColumnArr);
        ${operationTools.indexToUpperCase(tableName)} obj = ${operationTools.indexToLowerCase(tableName)}Service.update(${operationTools.indexToLowerCase(tableName)});
        ResponseUtils.responseJsonFilter(response, obj,${operationTools.indexToUpperCase(tableName)}Constants.filterselectColumnArr);
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/del${operationTools.indexToUpperCase(tableName)}")
    public Map del${operationTools.indexToUpperCase(tableName)}(HttpServletResponse response, @RequestParam(required = true) ${primarykeyJavaType} ${operationTools.indexToLowerCase(primarykey)}Arr[]) {
        ${operationTools.indexToLowerCase(tableName)}Service.del(${operationTools.indexToLowerCase(primarykey)}Arr);
        return BaseConstants.tableDelSuccess;
    }


<#list javaColumns as column>
<#if column.fktable>
    /**
     * 外键查询
     */
    @RequestMapping(value = "/fk${operationTools.indexToUpperCase(tableName)}Find${operationTools.indexToUpperCase(column.pkTableName)}")
    @ResponseBody
    public List fk${operationTools.indexToUpperCase(tableName)}Find${operationTools.indexToUpperCase(column.pkTableName)}(${column.javaType} ${operationTools.indexToLowerCase(column.pkTableColumn)}) {
        List<${operationTools.indexToUpperCase(tableName)}> ${operationTools.indexToLowerCase(tableName)}List = ${operationTools.indexToLowerCase(tableName)}Service.fk${operationTools.indexToUpperCase(tableName)}Find${operationTools.indexToUpperCase(column.pkTableName)}(${operationTools.indexToLowerCase(column.pkTableColumn)});
        return ${operationTools.indexToLowerCase(tableName)}List;
    }
</#if>
</#list>
}
