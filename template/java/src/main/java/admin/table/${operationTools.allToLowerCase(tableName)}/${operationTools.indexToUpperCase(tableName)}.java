package ${basePackage}.admin.table.${operationTools.allToLowerCase(tableName)};

<#list javaColumns as column>
    <#if column.fktable>
import ${basePackage}.admin.table.${operationTools.allToLowerCase(column.pkTableName)}.${operationTools.indexToUpperCase(column.pkTableName)};
    </#if>
</#list>
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "${tableName}", catalog = "")
public class ${operationTools.indexToUpperCase(tableName)} implements Serializable{
    private static final long serialVersionUID = ${operationTools.getserialVersionUID()}L;
<#-- ============================     字段列表     ======================== -->
<#list javaColumns as column>
    <#-- ============================    是否是主键     ======================== -->
    <#if column.primary>
    @Id
        <#if column.autoIncrement>
    @GeneratedValue(strategy=GenerationType.IDENTITY)
        </#if>
    </#if>
    <#-- ============================     是否是外键     ======================== -->
    <#if column.fktable>
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "${column.name}", referencedColumnName = "${column.pkTableColumn}")
    private ${operationTools.indexToUpperCase(column.pkTableName)} ${operationTools.indexToLowerCase(column.pkTableName)};
    <#else>
    @Basic
    ${interfaceToos.column(column)}
    <#if column.javaType == 'Date' >
    @JSONField(format="yyyy-MM-dd hh:mm:ss")
    </#if>
    private ${column.javaType} ${operationTools.indexToLowerCase(column.name)};
    </#if>
</#list>
}
