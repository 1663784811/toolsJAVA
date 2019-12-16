package cn.cyyaw.code.table.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "c_interface", catalog = "")
@org.hibernate.annotations.Table(appliesTo = "c_interface", comment = "接口")
public class CInterface implements Serializable {
    private static final long serialVersionUID = 1573661893761628L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "[id]", length = 10, unique = true, columnDefinition = "int COMMENT 'id'")
    private Integer id;
    @Basic
    @Column(name = "[tid]", length = 45, nullable = true, columnDefinition = "varchar(45) COMMENT 'tid'")
    private String tid;
    @Basic
    @Column(name = "[name]", nullable = true, columnDefinition = "varchar(255) COMMENT '接口'")
    private String name;
    @Basic
    @Column(name = "[url]", nullable = true, columnDefinition = "varchar(255) COMMENT 'url'")
    private String url;
    @Basic
    @Column(name = "[type]", length = 45, nullable = true, columnDefinition = "varchar(45) COMMENT '类型'")
    private String type;
    @Basic
    @Column(name = "[parameter]", nullable = true, columnDefinition = "text COMMENT '参数'")
    private String parameter;
    @Basic
    @Column(name = "[returnvalue]", nullable = true, columnDefinition = "text COMMENT '返回值'")
    private String returnvalue;
    @Basic
    @Column(name = "[createtime]", length = 19, nullable = true, columnDefinition = "datetime COMMENT '创建时间'")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    @Basic
    @Column(name = "[note]", nullable = true, columnDefinition = "varchar(255) COMMENT '备注'")
    private String note;
}
