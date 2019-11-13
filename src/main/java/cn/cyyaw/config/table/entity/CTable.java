package cn.cyyaw.config.table.entity;

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
@Table(name = "c_table", catalog = "")
public class CTable implements Serializable{
    private static final long serialVersionUID = 1573661893800365L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic
    @Column(name="id", length = 10 ,unique = true, columnDefinition = "int COMMENT ''")
    private Integer id;
    @Basic
    @Column(name="tid", length = 45 ,nullable = true, columnDefinition = "varchar(45) COMMENT ''")
    private String tid;
    @Basic
    @Column(name="tablename" ,nullable = true, columnDefinition = "varchar(255) COMMENT '表名'")
    private String tablename;
    @Basic
    @Column(name="note" ,nullable = true, columnDefinition = "varchar(255) COMMENT '备注'")
    private String note;
}
