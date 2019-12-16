package cn.cyyaw.code.table.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "c_field", catalog = "")
@org.hibernate.annotations.Table(appliesTo = "c_field", comment = "字段表")
public class CField implements Serializable {
    private static final long serialVersionUID = 15736618935283L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "[id]", length = 10, unique = true, columnDefinition = "int COMMENT ''")
    private Integer id;
    @Basic
    @Column(name = "[tid]", length = 45, nullable = true, columnDefinition = "varchar(45) COMMENT 'tid'")
    private String tid;
    @Basic
    @Column(name = "[columnname]", nullable = true, columnDefinition = "varchar(255) COMMENT '字段名'")
    private String columnname;
    @Basic
    @Column(name = "[dbtype]", nullable = true, columnDefinition = "varchar(255) COMMENT '数据库字段类型'")
    private String dbtype;
    @Basic
    @Column(name = "[defaultvalue]", nullable = true, columnDefinition = "varchar(255) COMMENT '默认值'")
    private String defaultvalue;
    @Basic
    @Column(name = "[note]", nullable = true, columnDefinition = "varchar(255) COMMENT '注释'")
    private String note;
    @Basic
    @Column(name = "[isprimary]", length = 10, nullable = true, columnDefinition = "int COMMENT '主键{0:否,1:是}'")
    private Integer isprimary;
    @Basic
    @Column(name = "[isautoincrement]", length = 10, nullable = true, columnDefinition = "int COMMENT '自增{0:否,1:是}'")
    private Integer isautoincrement;
    @Basic
    @Column(name = "[isunique]", length = 10, nullable = true, columnDefinition = "int COMMENT '唯一{0:否,1:是}'")
    private Integer isunique;
    @Basic
    @Column(name = "[isnull]", length = 10, nullable = true, columnDefinition = "int COMMENT '为null{0:否,1:是}'")
    private Integer isnull;
    @Basic
    @Column(name = "[isindex]", length = 10, nullable = true, columnDefinition = "int COMMENT '索引{0:否,1:是}'")
    private Integer isindex;
    @Basic
    @Column(name = "[indextype]", nullable = true, columnDefinition = "varchar(255) COMMENT '索引类型'")
    private String indextype;
    @Basic
    @Column(name = "[isfk]", length = 10, nullable = true, columnDefinition = "int COMMENT '外键{0:否,1:是}'")
    private Integer isfk;
    @Basic
    @Column(name = "[pktablename]", nullable = true, columnDefinition = "varchar(255) COMMENT '外键主表'")
    private String pktablename;
    @Basic
    @Column(name = "[pkcolumnname]", nullable = true, columnDefinition = "varchar(255) COMMENT '外键主表列'")
    private String pkcolumnname;
    @Basic
    @Column(name = "[fktablename]", nullable = true, columnDefinition = "varchar(255) COMMENT '外键表名'")
    private String fktablename;
    @Basic
    @Column(name = "[fkcolumnname]", nullable = true, columnDefinition = "varchar(255) COMMENT '外键表列'")
    private String fkcolumnname;
    @Basic
    @Column(name = "[key]", nullable = true, columnDefinition = "varchar(255) COMMENT '键值'")
    private String key;
    @Basic
    @Column(name = "[title]", nullable = true, columnDefinition = "varchar(255) COMMENT '名称'")
    private String title;
    @Basic
    @Column(name = "[type]", nullable = true, columnDefinition = "varchar(255) COMMENT '列表类型'")
    private String type;
    @Basic
    @Column(name = "[length]", length = 10, nullable = true, columnDefinition = "int COMMENT '长度'")
    private Integer length;
    @Basic
    @Column(name = "[isrequire]", length = 10, nullable = true, columnDefinition = "int COMMENT '是否必须{0:否,1:是}'")
    private Integer isrequire;
    @Basic
    @Column(name = "[regstr]", nullable = true, columnDefinition = "varchar(255) COMMENT '正则表达式'")
    private String regstr;
    @Basic
    @Column(name = "[message]", nullable = true, columnDefinition = "varchar(255) COMMENT '提示'")
    private String message;
    @Basic
    @Column(name = "[controltype]", nullable = true, columnDefinition = "varchar(255) COMMENT '控件类型'")
    private String controltype;
    @Basic
    @Column(name = "[format]", nullable = true, columnDefinition = "varchar(255) COMMENT '格式化'")
    private String format;
    @Basic
    @Column(name = "[iswhere]", length = 10, nullable = true, columnDefinition = "int COMMENT '查询条件{0:否,1:是}'")
    private Integer iswhere;
    @Basic
    @Column(name = "[isshowcolumn]", length = 10, nullable = true, columnDefinition = "int COMMENT '显示列表{0:否,1:是}'")
    private Integer isshowcolumn;
    @Basic
    @Column(name = "[javawhere]", nullable = true, columnDefinition = "varchar(255) COMMENT 'java条件'")
    private String javawhere;
    @Basic
    @Column(name = "[javatype]", nullable = true, columnDefinition = "varchar(255) COMMENT 'java类型'")
    private String javatype;
    @Basic
    @Column(name = "[ctableid]", nullable = true, columnDefinition = "varchar(255) COMMENT 'ctableid表tid'")
    private String ctableid;
}
