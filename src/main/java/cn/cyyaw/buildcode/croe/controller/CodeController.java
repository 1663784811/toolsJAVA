package cn.cyyaw.buildcode.croe.controller;

import cn.cyyaw.buildcode.croe.code.CreateCode;
import cn.cyyaw.buildcode.croe.code.InterfaceToos;
import cn.cyyaw.buildcode.croe.code.OperationTools;
import cn.cyyaw.buildcode.croe.code.TypeTools;
import cn.cyyaw.buildcode.croe.database.DataBase;
import cn.cyyaw.buildcode.croe.entity.java.JavaColumn;
import cn.cyyaw.buildcode.croe.entity.java.JavaData;
import cn.cyyaw.buildcode.mybatis.MybatisXmlMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class CodeController {


    public void buildCode(String url, String user, String pwd, String table) throws SQLException, ClassNotFoundException, IOException {
        url = url + "?user=" + user + "&password=" + pwd + "&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        DataBase dataBase = new DataBase("com.mysql.cj.jdbc.Driver", url, user, pwd);
        List<JavaData> tableList = dataBase.getTableList(table);
        createCode(tableList);
    }

    public void createCode(List<JavaData> tableList) throws IOException {
        CreateCode createCode = new CreateCode("template", "D:\\ab");
        OperationTools operationTools = new OperationTools();
        InterfaceToos interfaceToos = new InterfaceToos();
        if (null != tableList) {
            for (int i = 0; i < tableList.size(); i++) {
                JavaData javaData = tableList.get(i);
                String st = javaData.getTable().toLowerCase();
                if( !"hibernate_sequence".equals(st)){
                    List<JavaColumn> javaColumns = javaData.getJavaColumns();
                    Map<String, Object> map = new HashMap();
                    //===========================================

                    map.put("basePackage", "cn.cyyaw.weixin.admin");          //基础包
                    map.put("basePathVue", "");                   //基础路径
                    //===========================================
                    map.put("javaData", javaData);                        //数据表信息
                    map.put("javaColumns", javaColumns);    //数据字段列表
                    map.put("vueJsons", TypeTools.javaColumnList2VueJsonList(javaColumns));
                    //===========================================
                    map.put("operationTools", operationTools);//工具类
                    map.put("interfaceToos", interfaceToos);//工具类
                    map.put("__Table__", operationTools.indexToUpperCase(javaData.getTable()));// 表名首字母大写
                    map.put("__table__", operationTools.indexToLowerCase(javaData.getTable()));// 表名首字母小写
                    map.put("__Pk__", operationTools.indexToUpperCase(operationTools.getPK(javaColumns).getColumnName()));// 主键首字母大写
                    map.put("__pk__", operationTools.indexToLowerCase(operationTools.getPK(javaColumns).getColumnName()));// 主键首字母小写
                    map.put("__pk_all__", operationTools.allToLowerCase(operationTools.getPK(javaColumns).getColumnName()));// 主键all母小写
                    map.put("__pkJava__", operationTools.getPK(javaColumns).getJavaType());// 主键java首字母大写


                    //===========================================  扩展
                    map.put("mybatisXml", new MybatisXmlMapper(javaData));

                    //===========================================  常用变量
                    createCode.setDataMap(map);
                    System.out.println("正在生成文件。。。:" + tableList.get(i).getTableNote());
                    if (createCode.out()) {
                        System.out.println("生成文件成功");
                    } else {
                        System.out.println("生成文件失败");
                    }
                }
            }
        }
    }


}
