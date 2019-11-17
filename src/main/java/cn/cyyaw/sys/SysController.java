package cn.cyyaw.sys;


import cn.cyyaw.code.service.CFieldService;
import cn.cyyaw.code.service.CTableService;
import cn.cyyaw.code.table.dao.CFieldDao;
import cn.cyyaw.code.table.dao.CTableDao;
import cn.cyyaw.code.table.entity.CField;
import cn.cyyaw.code.table.entity.CTable;
import cn.cyyaw.jpa.BaseConstants;
import cn.cyyaw.sys.service.DbService;
import cn.cyyaw.util.buildcode.controller.CodeController;
import cn.cyyaw.util.buildcode.entity.java.JavaColumn;
import cn.cyyaw.util.buildcode.entity.java.JavaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RequestMapping("/sys")
@RestController
public class SysController {


    @Autowired
    protected DbService dbService;

    @Autowired
    private CTableDao cTableDao;

    @Autowired
    private CFieldDao cFieldDao;


    @Autowired
    private CodeController codeController;

    /**
     * 读取数据库结构
     */
    @PostMapping("/readstructure")
    public Map readstructure(@RequestBody Map<String, Object> map) throws SQLException, ClassNotFoundException {
        String url = (String) map.get("url");
        String user = (String) map.get("user");
        String password = (String) map.get("password");
        url = url + "?user=" + user + "&password=" + password + "&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        dbService.readstructure(url, user, password);
        return BaseConstants.statusMessage(true, "读取成功！");
    }


    /**
     * 生成代码
     */
    @PostMapping("/createCode")
    public Map createCode(@RequestBody Integer[] idArr) throws IOException {
        List<CTable> cTableList = cTableDao.findByidIsIn(idArr);
        List<JavaData> tableList = new ArrayList<>();
        if (null != cTableList && cTableList.size() > 0) {
            for (int i = 0; i < cTableList.size(); i++) {
                JavaData javaData = new JavaData();
                CTable cTable = cTableList.get(i);
                //===================
                javaData.setDatabase(cTable.getDatabase());
                javaData.setTable(cTable.getTablename());
                javaData.setTableNote(cTable.getNote());
                javaData.setTableType(cTable.getTabletype());
                //===================
                String tid = cTable.getTid();
                List<CField> cFieldList = cFieldDao.findByCtableid(tid);
                if (null != cFieldList && cFieldList.size() > 0) {
                    List<JavaColumn> javaColumnList = new ArrayList<>();
                    for (int j = 0; j < cFieldList.size(); j++) {
                        CField cField = cFieldList.get(j);
                        JavaColumn javaColumn = new JavaColumn();
                        javaColumn.setColumnName(cField.getColumnname());
                        javaColumn.setDbType(cField.getDbtype());
                        javaColumn.setLength(cField.getLength());
                        javaColumn.setJavaType(cField.getJavatype());
                        javaColumn.setDefaultValue(cField.getDefaultvalue());
                        javaColumn.setNote(cField.getNote());
                        javaColumn.setIsAutoIncrement(cField.getIsautoincrement() != null && cField.getIsautoincrement() == 1);
                        javaColumn.setIsNull(cField.getIsnull() != null && cField.getIsnull() == 1);
                        javaColumn.setIsPrimary(cField.getIsprimary() != null && cField.getIsprimary() == 1);
                        javaColumn.setPkTableName(cField.getPktablename());
                        javaColumn.setPkColumnName(cField.getPkcolumnname());
                        javaColumn.setIsFk(cField.getIsfk() != null && cField.getIsfk() == 1);
                        javaColumn.setFkTableName(cField.getFktablename());
                        javaColumn.setFkColumnName(cField.getFkcolumnname());
                        javaColumn.setIsUnique(cField.getIsunique() != null && cField.getIsunique() == 1);
                        javaColumn.setIsIndex(cField.getIsindex() != null && cField.getIsindex() == 1);
                        javaColumn.setIndexType(cField.getIndextype());
                        javaColumnList.add(javaColumn);
                    }
                    javaData.setJavaColumns(javaColumnList);
                }
                tableList.add(javaData);
            }
        }
        //生成代码
        codeController.createCode(tableList);
        //打包成压缩文件
        return BaseConstants.statusMessage(true, "生成代码成功！");
        //下载
    }
}
