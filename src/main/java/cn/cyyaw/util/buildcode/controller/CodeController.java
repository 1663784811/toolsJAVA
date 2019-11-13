package cn.cyyaw.util.buildcode.controller;

import cn.cyyaw.util.buildcode.code.CreateCode;
import cn.cyyaw.util.buildcode.code.InterfaceToos;
import cn.cyyaw.util.buildcode.code.OperationTools;
import cn.cyyaw.util.buildcode.code.TypeTools;
import cn.cyyaw.util.buildcode.database.DataBase;
import cn.cyyaw.util.buildcode.entity.java.JavaColumn;
import cn.cyyaw.util.buildcode.entity.java.JavaData;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeController {

    public void buildCode(String url, String user, String pwd, String table) throws SQLException, ClassNotFoundException, IOException {
        CreateCode createCode = new CreateCode("template", "I:\\ab");
        OperationTools operationTools = new OperationTools();
        InterfaceToos interfaceToos = new InterfaceToos();
        url = url + "?user=" + user + "&password=" + pwd + "&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        DataBase dataBase = new DataBase("com.mysql.cj.jdbc.Driver", url, user, pwd);
        List<JavaData> tableList = dataBase.getTableList();


        if (null != tableList) {
            for (int i = 0; i < tableList.size(); i++) {
                JavaData javaData = tableList.get(i);
                List<JavaColumn> javaColumns = javaData.getJavaColumns();
                if (table==null || table.equals(javaData.getTable())) {
                    Map<String, Object> map = new HashMap();
                    //===========================================
                    map.put("basePackage", "cn.cyyaw.flysandy.admin");          //基础包
                    map.put("basePathVue", "/admin");                   //基础路径
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
