package cn.cyyaw.code.table.entity;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;

@Data
@Entity
@Table(name = "c_table", catalog = "")
@org.hibernate.annotations.Table(appliesTo = "c_table", comment = "数据表")
public class CTable implements Serializable {
    private static final long serialVersionUID = 1573661893800365L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "[id]", length = 10, unique = true, columnDefinition = "int COMMENT ''")
    private Integer id;
    @Basic
    @Column(name = "[tid]", length = 45, nullable = true, columnDefinition = "varchar(45) COMMENT ''")
    private String tid;
    @Basic
    @Column(name = "[database]", nullable = true, columnDefinition = "varchar(255) COMMENT '数据库'")
    private String database;
    @Basic
    @Column(name = "[tablename]", nullable = true, columnDefinition = "varchar(255) COMMENT '表名'")
    private String tablename;
    @Basic
    @Column(name = "[tabletype]", nullable = true, columnDefinition = "varchar(255) COMMENT '类型'")
    private String tabletype;
    @Basic
    @Column(name = "[note]", nullable = true, columnDefinition = "varchar(255) COMMENT '备注'")
    private String note;
    @Basic
    @Column(name = "[url]", nullable = true, columnDefinition = "varchar(255) COMMENT '连接url'")
    private String url;
    @Basic
    @Column(name = "[user]", nullable = true, columnDefinition = "varchar(255) COMMENT '连接用户名'")
    private String user;
    @Basic
    @Column(name = "[pwd]", nullable = true, columnDefinition = "varchar(255) COMMENT '连接密码'")
    private String pwd;
}
