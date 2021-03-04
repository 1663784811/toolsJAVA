package cn.cyyaw.buildcode.mybatis;

import cn.cyyaw.buildcode.croe.code.OperationTools;
import cn.cyyaw.buildcode.croe.entity.java.JavaColumn;
import cn.cyyaw.buildcode.croe.entity.java.JavaData;

import java.util.List;

public class MybatisXmlMapper {

    private List<JavaColumn> javaColumns;
    private String table;

    public MybatisXmlMapper(JavaData javaData ){
        this.table = javaData.getTable();
        this.javaColumns = javaData.getJavaColumns();
    }

    public String getBaseResultMap(){
        String xml = "<resultMap id=\"BaseResultMap\" type=\"com.cf.carpark.domain."+OperationTools.indexToUpperCase(table)+"\" >\n";
        for (int i = 0; i < javaColumns.size(); i++) {
            JavaColumn c = javaColumns.get(i);
            if(c.getIsPrimary()){
               xml+= "        <id column=\""+c.getColumnName()+"\" property=\""+ OperationTools.delSpecial(c.getColumnName())+"\" jdbcType=\""+c.getDbType()+"\" />\n";
            }else{
                xml+= "        <result column=\""+c.getColumnName()+"\" property=\""+OperationTools.delSpecial(c.getColumnName())+"\" jdbcType=\""+c.getDbType()+"\" />\n";
            }
        }
        xml+= "  </resultMap>";
        return xml;
    }











}
