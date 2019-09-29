package cn.cyyaw.util.buildcode.controller;

import cn.cyyaw.util.buildcode.code.CreateCode;
import cn.cyyaw.util.buildcode.code.InterfaceToos;
import cn.cyyaw.util.buildcode.code.OperationTools;
import cn.cyyaw.util.buildcode.code.TypeTools;
import cn.cyyaw.util.buildcode.entity.database.DataBase;
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
                if ((table == null || table.trim().equals("")) || table.equals(javaData.getTable())) {
                    Map<String, Object> map = new HashMap();
                    //===========================================
                    map.put("basePackage", "cn.cyyaw.weixin");          //基础包
                    map.put("basePathVue", "/admin");                   //基础路径

                    //===========================================
                    map.put("tableName", javaData.getTable());    //数据表
                    map.put("tableNote", javaData.getTableNote());  //表注释
                    map.put("primarykey", javaData.getPrimarykey());  // 主键
                    map.put("primarykeyJavaType", javaData.getPrimarykeyJavaType());  //主键类型
                    map.put("javaColumns", javaData.getJavaColumns());    //
                    map.put("vueJsons", TypeTools.javaColumnList2VueJsonList(javaData.getJavaColumns()));
                    //===========================================
                    map.put("operationTools", operationTools);//工具类
                    map.put("interfaceToos", interfaceToos);//工具类
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
