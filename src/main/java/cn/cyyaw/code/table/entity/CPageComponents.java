package cn.cyyaw.code.table.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "c_page_components", catalog = "")
@org.hibernate.annotations.Table(appliesTo = "c_page_components", comment = "页面组件")
public class CPageComponents implements Serializable {
    private static final long serialVersionUID = 15736618935283L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "[id]", length = 10, unique = true, columnDefinition = "int COMMENT 'id'")
    private Integer id;
    @Basic
    @Column(name = "[tid]", length = 45, nullable = true, columnDefinition = "varchar(45) COMMENT 'tid'")
    private String tid;
    @Basic
    @Column(name = "[note]", nullable = true, columnDefinition = "varchar(255) COMMENT '注释'")
    private String note;
    @Basic
    @Column(name = "[createtime]", length = 19, nullable = true, columnDefinition = "datetime COMMENT '创建时间'")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    @Basic
    @Column(name = "[pageid]", length = 45, nullable = true, columnDefinition = "varchar(45) COMMENT '页面ID'")
    private String pageid;
    @Basic
    @Column(name = "[componentsid]", length = 45, nullable = true, columnDefinition = "varchar(45) COMMENT '组件ID'")
    private String componentsid;
    @Basic
    @Column(name = "[sort]", length = 10, unique = true, columnDefinition = "int COMMENT '排序'")
    private Integer sort;

    @Basic
    @Column(name = "[name]", length = 45, nullable = true, columnDefinition = "varchar(45) COMMENT '名称'")
    private String name;
    @Basic
    @Column(name = "[icon]", length = 255, nullable = true, columnDefinition = "varchar(255) COMMENT 'icon图标'")
    private String icon;
    @Basic
    @Column(name = "[tag]", nullable = true, columnDefinition = "text COMMENT '标签'")
    private String tag;
    @Basic
    @Column(name = "[style]", nullable = true, columnDefinition = "text COMMENT '样式'")
    private String style;
    @Basic
    @Column(name = "[importJs]", nullable = true, columnDefinition = "text COMMENT '导入js'")
    private String importJs;
    @Basic
    @Column(name = "[components]", nullable = true, columnDefinition = "text COMMENT '组件'")
    private String components;
    @Basic
    @Column(name = "[data]", nullable = true, columnDefinition = "text COMMENT '数据'")
    private String data;
    @Basic
    @Column(name = "[methods]", nullable = true, columnDefinition = "text COMMENT '方法'")
    private String methods;
    @Basic
    @Column(name = "[computed]", nullable = true, columnDefinition = "text COMMENT '计算属性'")
    private String computed;
    @Basic
    @Column(name = "[mounted]", nullable = true, columnDefinition = "text COMMENT '初始化方法'")
    private String mounted;
}
