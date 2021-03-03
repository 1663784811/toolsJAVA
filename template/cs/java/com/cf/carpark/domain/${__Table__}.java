package com;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModelProperty;

@Data
public class ${__Table__} implements Serializable{
    private static final long serialVersionUID = ${operationTools.getserialVersionUID()}L;
<#-- ============================     字段列表     ======================== -->
<#list javaColumns as column>

    @ApiModelProperty("${column.note}")
    private ${column.javaType} ${operationTools.indexToLowerCase(column.columnName)};
</#list>
}
