package cn.cyyaw.util.buildcode.code;



import cn.cyyaw.util.buildcode.entity.java.JavaColumn;
import cn.cyyaw.util.buildcode.entity.vue.VueJson;

import java.util.ArrayList;
import java.util.List;

/**
 * 类型转换工具
 */
public class TypeTools {


    /**
     * 数据库类型转换成java类型
     *
     * @param type
     * @return
     */
    public static String dbType2JavaType(String type) {
        String objType = null;
        if (type.equals("INT") || type.equals("int")
                || type.equals("TINYINT") || type.equals("tinyint")
                || type.equals("SMALLINT") || type.equals("smallint")
                || type.equals("MEDIUMINT") || type.equals("mediumint")
        ) {
            objType = "Integer";
        } else if (type.equals("BIGINT") || type.equals("bigint")) {
            objType = "BigInteger";
        } else if (type.equals("FLOAT") || type.equals("float")) {
            objType = "Float";
        } else if (type.equals("DOUBLE") || type.equals("double")) {
            objType = "Double";
        } else if (type.equals("DATE") || type.equals("date")
                || type.equals("DATETIME") || type.equals("datetime")
                || type.equals("TIMESTAMP") || type.equals("timestamp")
                || type.equals("TIME") || type.equals("time")
                || type.equals("YEAR") || type.equals("year")
        ) {
            //============= 日期
            objType = "Date";

        } else if (type.equals("VARCHAR") || type.equals("varchar")
                || type.equals("TEXT") || type.equals("text")
                || type.equals("TINYTEXT") || type.equals("tinytext")
                || type.equals("MEDIUMTEXT") || type.equals("mediumtext")
                || type.equals("LONGTEXT") || type.equals("longtext")
        ) {
            //============= 数值字符
            objType = "String";
        }
        return objType;
    }


    public static String javaType2CodeType(String type) {
        String objType = "";
        switch (type) {
            case "string":
                objType = "like";
                break;
            case "integer":
                objType = "equals";
                break;
            case "biginteger":
                objType = "equals";
                break;
            case "float":
                objType = "equals";
                break;
            case "double":
                objType = "equals";
                break;
            case "date":
                objType = "equals";
                break;
        }
        return objType;
    }

    /**
     * 数据库类型转换 组件类型
     *
     * @param type
     * @return
     */
    public static String javaType2ControlType(String type) {
        String objType = null;
        if (type.equals("INT") || type.equals("int")
                || type.equals("TINYINT") || type.equals("tinyint")
                || type.equals("SMALLINT") || type.equals("smallint")
                || type.equals("MEDIUMINT") || type.equals("mediumint")
                || type.equals("BIGINT") || type.equals("bigint")
        ) {
            objType = "integer";
        } else if (type.equals("FLOAT") || type.equals("float")
                || type.equals("DOUBLE") || type.equals("double")
        ) {
            objType = "float";
        } else if (type.equals("DATE") || type.equals("date")
        ) {
            //============= 日期
            objType = "date";
        } else if (type.equals("DATETIME") || type.equals("datetime")
                || type.equals("TIMESTAMP") || type.equals("timestamp")
                || type.equals("YEAR") || type.equals("year")
        ) {
            //============= 日期时间
            objType = "datetime";
        } else if (type.equals("TIME") || type.equals("time")) {
            //============= 时间
            objType = "time";
        } else if (type.equals("TEXT") || type.equals("text")
                || type.equals("MEDIUMTEXT") || type.equals("mediumtext")
                || type.equals("LONGTEXT") || type.equals("longtext")
        ) {
            //============= 数值字符
            objType = "textarea";
        } else {
            //============= 数值字符
            objType = "input";
        }
        return objType;
    }

    /**
     * 日期格式化
     *
     * @param dateType
     * @return
     */
    public static String dateType2Format(String dateType) {
        String str = "";
        switch (dateType) {
            case "date":
                str = "yyyy-MM-dd";
                break;
            case "datetime":
                str = "yyyy-MM-dd HH:mm:ss";
                break;
            case "time":
                str = "HH:mm:ss";
                break;
        }
        return str;
    }


    /**
     * @param javaColumnList
     * @return
     */
    public static List<VueJson> javaColumnList2VueJsonList(List<JavaColumn> javaColumnList) {
        ArrayList<VueJson> vueJsonArrayList = new ArrayList<VueJson>();
        if (javaColumnList != null) {
            for (int i = 0; i < javaColumnList.size(); i++) {
                vueJsonArrayList.add(javaColumn2VueJson(javaColumnList.get(i)));
            }
        }
        return vueJsonArrayList;
    }

    /**
     * @param javaColumn
     * @return
     */
    public static VueJson javaColumn2VueJson(JavaColumn javaColumn) {
        VueJson vueJson = new VueJson();
        if (null != javaColumn) {
            vueJson.setKey(javaColumn.getName());
            vueJson.setTitle(javaColumn.getNote());
            vueJson.setType(javaColumn.getPrimary() ? "selection" : "html");
            vueJson.setLength(javaColumn.getLenth());
            vueJson.setRegStr("");
            vueJson.setRequire(false);
            vueJson.setMessage(javaColumn.getNote());
            if (javaColumn.getPrimary()) {
                vueJson.setControlType("hidden");
            } else {
                vueJson.setControlType(javaType2ControlType(javaColumn.getDbType()));
            }
            vueJson.setFormat(dateType2Format(javaType2ControlType(javaColumn.getDbType())));

            vueJson.setWhere(!javaColumn.getPrimary());
            vueJson.setShowColumn(true);

            vueJson.setJavaType(dbType2JavaType(javaColumn.getDbType()).toLowerCase());
            vueJson.setJavaWhere(javaType2CodeType(dbType2JavaType(javaColumn.getDbType()).toLowerCase()));

        }
        return vueJson;
    }

}
