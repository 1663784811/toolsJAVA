package cn.cyyaw.util.buildcode.code;


import cn.cyyaw.util.buildcode.entity.java.JavaColumn;

import java.util.Date;
import java.util.Random;


public class OperationTools {


    /**
     * 生成注解数据唯一
     *
     * @return
     */
    public static String dbTypeNull(JavaColumn javaColumn) {
        if (null != javaColumn) {
            if (!javaColumn.getNull()) {
                return " ,nullable = true";
            }
        }
        return "";
    }

    /**
     * 生成注解数据唯一
     *
     * @return
     */
    public static String dbTypeUnique(JavaColumn javaColumn) {
        if (null != javaColumn) {
            if (javaColumn.getUnique()) {
                return " ,unique = true";
            }
        }
        return "";
    }

    /**
     * 生成注解数据库类型
     *
     * @return
     */
    public static String dbTypeEntity(JavaColumn javaColumn) {
        if (null != javaColumn) {
            String dbType = allToLowerCase(javaColumn.getDbType());
            if (dbType.equals("text") || dbType.equals("int") || dbType.equals("datetime")) {
                return ", columnDefinition = \"" + dbType + dbTypeComment(javaColumn) + "\"";
            } else if (dbType.equals("varchar")) {
                return ", columnDefinition = \"" + dbType + "(" + javaColumn.getLenth() + ")" + dbTypeComment(javaColumn) + "\"";
            }
        }
        return "";
    }

    /**
     * 生成注释
     *
     * @param javaColumn
     * @return
     */
    public static String dbTypeComment(JavaColumn javaColumn) {
        if (null != javaColumn) {
            String note = javaColumn.getNote();
            if (note != null) {
                return " COMMENT '" + note + "'";
            }
        }
        return "";
    }

    /**
     * 生成注解长度
     *
     * @return
     */
    public static String dbTypeLength(JavaColumn javaColumn) {
        if (null != javaColumn) {
            Integer lenth = javaColumn.getLenth();
            if (lenth <= 225) {
                return ", length = " + lenth;
            }
        }
        return "";
    }

    /**
     * 去除特殊字符，首字母大写
     *
     * @param str
     * @return
     */
    public static String indexToUpperCase(String str) {
        String newStr = "";
        if (null != str && str.length() > 0) {
            String s = delSpecial(str);
            if (s.length() > 0) {
                newStr = s.substring(0, 1).toUpperCase() + s.substring(1);
            }
        }
        return newStr;
    }

    /**
     * 去除特殊字符，首字母小写
     *
     * @param str
     * @return
     */
    public static String indexToLowerCase(String str) {
        String newStr = "";
        if (null != str && str.length() > 0) {
            String s = delSpecial(str);
            if (s.length() > 0) {
                newStr = s.substring(0, 1).toLowerCase() + s.substring(1);
            }
        }
        return newStr;
    }


    /**
     * 去除特殊字符，所有字母小写
     *
     * @return
     */
    public static String allToLowerCase(String str) {
        String newStr = "";
        if (null != str && str.length() > 0) {
            newStr = delSpecial(str).toLowerCase();
        }
        return newStr;
    }

    /**
     * 去除特殊字符，所有字母大写
     *
     * @return
     */
    public static String allToUpperCase(String str) {
        String newStr = "";
        if (null != str && str.length() > 0) {
            newStr = delSpecial(str).toUpperCase();
        }
        return newStr;
    }


    /**
     * 去除特殊字符，下一个字母大写
     *
     * @return
     */
    public static String delSpecial(String str) {
        StringBuilder newStr = new StringBuilder();
        if (null != str && !str.equals("")) {
            boolean isup = false;
            String str1 = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLOMNOPQRSTUVWXYZ";
            for (int i = 1; i <= str.length(); i++) {
                String substring = str.substring(i - 1, i);
                if (str1.indexOf(substring) == -1) {
                    //找到特殊字符
                    isup = true;
                } else {
                    if (isup) {
                        newStr.append(substring.toUpperCase());
                        isup = false;
                    } else {
                        newStr.append(substring);
                    }
                }
            }
        }
        return newStr + "";
    }


    public static String getserialVersionUID() {
        long time = 1;
        time = new Date().getTime();
        Random random = new Random();
        int i = random.nextInt(1000);
        return time + "" + i;
    }


}
