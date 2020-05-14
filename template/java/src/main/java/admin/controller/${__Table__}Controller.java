package ${basePackage}.controller;

import ${basePackage}.service.${__Table__}Service;
import ${basePackage}.table.entity.${__Table__};
import ${basePackage}.table.entityconst.${__Table__}Const;

import cn.cyyaw.jpa.BaseConstants;
import cn.cyyaw.util.entity.SelectEntity;
import cn.cyyaw.util.tools.*;

<#list javaColumns as column>
    <#if column.isFk>
import ${basePackage}.table.entity.${operationTools.indexToUpperCase(column.pkTableName)};
import ${basePackage}.table.entityconst.${operationTools.indexToUpperCase(column.pkTableName)}Const;
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
import java.util.Date;

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
public class ${__Table__}Controller {

    @Autowired
    private ${__Table__}Service ${__table__}Service;

    /**
     * 表:${javaData.table} ===> 所有:带条件
     */
    @GetMapping(value = "/findAll${__Table__}")
    public void findAll${__Table__}(HttpServletResponse response, String jsonStr, SelectEntity selectEntity) {
        List<${__Table__}> list = ${__table__}Service.findAll(jsonStr, selectEntity);
        ResponseUtils.responseJsonFilter(response, list,${__Table__}Const.filterselectColumnArr);
    }

    /**
     * 分页条件查询
     */
    @GetMapping(value = "/findPage${__Table__}")
    public void findPage${__Table__}(HttpServletResponse response,String jsonStr,  SelectEntity selectEntity) {
        PageRequest pageRequest = JpaUtils.getPageRequest(selectEntity);
        Page<${__Table__}> page = ${__table__}Service.findPage(jsonStr, pageRequest);
        ResponseUtils.responseJsonFilter(response, PageUtil.pageFormat(page),${__Table__}Const.filterselectColumnArr);
    }

    /**
     * 根据ID查询
     */
    @GetMapping(value = "/findId${__Table__}")
    public void findId${__Table__}(HttpServletResponse response,@RequestParam ${__pkJava__} ${__pk__}) {
        ${__Table__} obj = ${__table__}Service.findId(${__pk__});
        ResponseUtils.responseJsonFilter(response, obj,${__Table__}Const.filterselectColumnArr);
    }


    /**
     * 添加或修改
     */
    @PostMapping(value = "/save${__Table__}")
    public void save${__Table__}(HttpServletResponse response,@RequestBody ${__Table__} ${__table__}) {
        ${__Table__} obj = null;
        //添加
        ${__pkJava__} id = ${__table__}.get${operationTools.indexToUpperCase( __Pk__ )}();
        if (null == id) {
            //添加
            log.info("添加:{}", ${__table__});
            WhyBeanUtils.filterField(${__table__}, ${__Table__}Const.filteraddColumnArr);
<#list javaColumns as column>
        <#if column.columnName == 'tid' >
            ${__table__}.setTid(WhyStringUtil.getUUID());
        </#if>
        <#if column.columnName == 'createtime' >
            ${__table__}.setCreatetime(new Date());
        </#if>
</#list>
            obj = ${__table__}Service.save(${__table__});
        } else {
            //修改
            log.info("修改:{}", ${__table__});
            ${__Table__} ${__table__}1 = ${__table__}Service.findId(${__pk__});
            Assert.notNull(${__table__}1, "操作失败！");
            WhyBeanUtils.filterField(${__table__}, ${__Table__}Const.filteraddColumnArr);
            obj = ${__table__}Service.save(${__table__});
        }
        ResponseUtils.responseJsonFilter(response, obj,${__Table__}Const.filterselectColumnArr);
    }

    /**
     * 删除
     */
    @PostMapping(value = "/del${__Table__}")
    public Map del${__Table__}( @RequestBody ${__pkJava__} ${__pk__}Arr[]) {
        ${__table__}Service.del(${__pk__}Arr);
        return BaseConstants.tableDelSuccess;
    }

<#list javaColumns as column>
<#if column.isFk>
    /**
     * 外键查询
     */
    @GetMapping(value = "/fk${__Table__}Find${operationTools.indexToUpperCase(column.pkTableName)}")
    @ResponseBody
    public List fk${__Table__}Find${operationTools.indexToUpperCase(column.pkTableName)}(${column.javaType} ${operationTools.indexToLowerCase(column.pkTableColumn)}) {
        List<${__Table__}> ${__table__}List = ${__table__}Service.fk${__Table__}Find${operationTools.indexToUpperCase(column.pkTableName)}(${operationTools.indexToLowerCase(column.pkTableColumn)});
        return ${__table__}List;
    }
</#if>
</#list>
}
