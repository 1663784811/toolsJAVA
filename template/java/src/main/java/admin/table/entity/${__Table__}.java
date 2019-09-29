package ${basePackage}.table.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "${tableName}", catalog = "")
public class ${operationTools.indexToUpperCase(tableName)} implements Serializable{
    private static final long serialVersionUID = ${operationTools.getserialVersionUID()}L;
<#-- ============================     字段列表     ======================== -->
<#list javaColumns as column>
    <#-- ============================    是否是主键     ======================== -->
    <#if column.isPrimary>
    @Id
        <#if column.isAutoIncrement>
    @GeneratedValue(strategy=GenerationType.IDENTITY)
        </#if>
    </#if>
    <#-- ============================     是否是外键     ======================== -->
    <#if column.isFktable>
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "${column.name}", referencedColumnName = "${column.pkTableColumn}")
    private ${operationTools.indexToUpperCase(column.pkTableName)} ${operationTools.indexToLowerCase(column.pkTableName)};
    <#else>
    @Basic
    ${interfaceToos.column(column)}
    <#if column.javaType == 'Date' >
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    </#if>
    private ${column.javaType} ${operationTools.indexToLowerCase(column.name)};
    </#if>
</#list>
}
