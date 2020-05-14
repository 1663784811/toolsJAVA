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
@Table(name = "${javaData.table}")
@org.hibernate.annotations.Table(appliesTo = "${javaData.table}", comment = "${javaData.tableNote}")
public class ${__Table__} implements Serializable{
    private static final long serialVersionUID = ${operationTools.getserialVersionUID()}L;
<#-- ============================     字段列表     ======================== -->
<#list javaColumns as column>
    <#-- ============================    是否是主键     ======================== -->
    <#if column.isPrimary>
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    </#if>
    <#-- ============================     是否是外键     ======================== -->
    <#if column.isFk>
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "${column.name}", referencedColumnName = "${column.pkTableColumn}")
    private ${operationTools.indexToUpperCase(column.pkTableName)} ${operationTools.indexToLowerCase(column.pkTableName)};
    <#else>
    @Basic
    ${interfaceToos.column(column)}
    <#--=======  是否是时间类型  ======-->
    <#if column.javaType == 'Date' >
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    </#if>
    private ${column.javaType} ${operationTools.indexToLowerCase(column.columnName)};
    </#if>
</#list>
}
