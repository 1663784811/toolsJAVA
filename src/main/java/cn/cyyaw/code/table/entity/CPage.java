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
@Table(name = "c_page", catalog = "")
@org.hibernate.annotations.Table(appliesTo = "c_page", comment = "页面")
public class CPage implements Serializable {
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
    @Column(name = "[name]", nullable = true, columnDefinition = "varchar(255) COMMENT '页面名'")
    private String name;
    @Basic
    @Column(name = "[pageicon]", nullable = true, columnDefinition = "varchar(255) COMMENT '图标'")
    private String pageicon;
    @Basic
    @Column(name = "[pid]", length = 45, nullable = true, columnDefinition = "varchar(45) COMMENT '父级ID'")
    private String pid;
}
