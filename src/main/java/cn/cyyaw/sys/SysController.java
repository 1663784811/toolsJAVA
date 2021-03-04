package cn.cyyaw.sys;


import cn.cyyaw.code.service.CPageComponentsService;
import cn.cyyaw.code.table.dao.CFieldDao;
import cn.cyyaw.code.table.dao.CTableDao;
import cn.cyyaw.code.table.entity.CField;
import cn.cyyaw.code.table.entity.CPageComponents;
import cn.cyyaw.code.table.entity.CTable;
import cn.cyyaw.jpa.BaseConstants;
import cn.cyyaw.sys.service.DbService;
import cn.cyyaw.buildcode.croe.controller.CodeController;
import cn.cyyaw.buildcode.croe.entity.java.JavaColumn;
import cn.cyyaw.buildcode.croe.entity.java.JavaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
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

    @Autowired
    private CPageComponentsService cPageComponentsService;


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
     * 生成页面
     */
    @RequestMapping("/createPage")
    public void createPage(String tid,String template) {
        CPageComponents cPageComponents = new CPageComponents();
        cPageComponents.setPageid(tid);
        List<CPageComponents> byExample = cPageComponentsService.findByExample(cPageComponents);





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


    @RequestMapping("/downloadFile")
    public void downloadFile(HttpServletResponse response, String name) throws IOException {
        File file = new File(name);
        //如果文件不存在
//        if (!file.exists()) {
//            request.setAttribute("message", "您要下载的资源已被删除！！");
//            request.setAttribute("flag", "1");
//            return MANAGER_APK_DECOMPILE_UPDATE_URL;
//        }
        // 取得文件名。
        String filename = file.getName();
        // 取得文件的后缀名。
        String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
        // 以流的形式下载文件。
        InputStream fis = new BufferedInputStream(new FileInputStream(file));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        // 清空response
        response.reset();
        // 设置response的Header
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
        response.addHeader("Content-Length", "" + file.length());
        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
    }


}
