package cn.cyyaw.util.file;


import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.*;

/**
 * 对比两个文件，拿出json部分，再对比，以第一份为基准与第二份生成一份目标文件
 */
public class File2File {

    public static void main(String arg[]) throws IOException {


        String str = File2File.readFile("I:\\ab\\vue\\src\\jsonObj\\gOrder.js");
        String str2 = File2File.readFile("G:\\shop\\wx\\src\\config\\jsonObj\\gOrder.js");
        String delNote = File2File.delNote(str);
        String delNote2 = File2File.delNote(str2);
        List<String> stringList = File2File.getJsonStr(delNote);
        List<String> stringList2 = File2File.getJsonStr(delNote2);
        if (stringList.size() == 1 && stringList2.size() == 1) {
            Map<String, Object> jsonStr = JSONObject.parseObject(stringList.get(0));
            Map<String, Object> jsonStr2 = JSONObject.parseObject(stringList2.get(0));
            List<Map<String, Object>> fieldList = (List<Map<String, Object>>) jsonStr.get("fieldList");
            List<Map<String, Object>> fieldList2 = (List<Map<String, Object>>) jsonStr2.get("fieldList");
            for (int i = 0; i < fieldList2.size(); i++) {
                boolean h = false;
                for (int j = 0; j < fieldList.size(); j++) {
                    if (fieldList2.get(i).get("key").equals(fieldList.get(i).get("key"))) {
                        h = true;
                    }
                }
                if (!h) {
                    fieldList.add(fieldList2.get(i));
                }
            }
            for (int i = 0; i < fieldList.size(); i++) {
                boolean h = false;
                for (int j = 0; j < fieldList2.size(); j++) {
                    if (fieldList2.get(i).get("key").equals(fieldList.get(i).get("key"))) {
                        h = true;
                    }
                }
                if (!h) {
                    fieldList.remove(i);
                }
            }
            Map<String, Object> stringObjectMap = File2File.File2File(jsonStr, jsonStr2);
            stringObjectMap.put("fieldList", fieldList);
            String stringDate = JSONObject.toJSONString(stringObjectMap);
            String str22 = "G:\\shop\\wx\\src\\config\\jsonObj\\gOrder2.js";


            FileOutputStream fos = new FileOutputStream(new File(str22));
            byte[] bytes = stringDate.getBytes();
            fos.write(bytes);
            fos.flush();
            fos.close();
        }
    }

    /**
     * 对比json 字符串 以第一份为基准与
     */
    public static Map<String, Object> File2File(Map<String, Object> obj1, Map<String, Object> obj2) {
        Set<String> strings1 = obj1.keySet();
        Set<String> strings2 = obj2.keySet();
        for (String s2 : strings2) {
            boolean h = false;
            for (String s1 : strings1) {
                if (s1.equals(s2)) {
                    h = true;
                }
            }
            if (!h) {
                obj1.put(s2, obj2.get(s2));
            }
        }
        for (String s1 : strings1) {
            boolean h = false;
            for (String s2 : strings2) {
                if (s1.equals(s2)) {
                    h = true;
                }
            }
            if (!h) {
                obj1.remove(s1);
            }
        }
        return obj1;
    }


    /**
     * 判断字符串是否是json格式
     */
    private static boolean isjson(String str) {
        try {
            JSONObject jsonStr = JSONObject.parseObject(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 取出json 字符串
     *
     * @return
     */
    public static List<String> getJsonStr(String str) {
        List<String> stringList = new ArrayList<String>();
        for (int i = 0; i < str.length(); i++) {
            StringBuffer sb = new StringBuffer();
            char c = str.charAt(i);
            if (c == '{') {
                sb.append(c);
                int j = 1;
                while (++i < str.length() && j > 0) {
                    char n = str.charAt(i);
                    sb.append(n);
                    if (n == '}') {
                        if (--j <= 0) {
                            String json = new String(sb);
                            if (isjson(json)) {
                                stringList.add(json);
                            }
                        }
                    } else if (n == '{') {
                        j++;
                    }
                }
            }
        }
        return stringList;
    }


    /**
     * 初步处理，删除注释
     */
    public static String delNote(String str) {
        StringBuffer sb = new StringBuffer();
        boolean s = true;
        for (int i = 0; i < str.length(); i++) {
            //查找 /
            char c = str.charAt(i);
            if (c == '"') s = !s;
            if (c == '\n') s = true;

            if (s && c == '/' && ++i < str.length()) {
                char t = str.charAt(i);
                if (t == '/') {
                    //查找换行符
                    boolean b = true;
                    while (b && ++i < str.length()) {
                        char n = str.charAt(i);
                        if (n == '\n') {
                            b = false;
                            sb.append(n);
                        }
                    }
                } else if (t == '*') {
                    //  查找 */
                    boolean b = true;
                    while (b && ++i < str.length()) {
                        char n = str.charAt(i);
                        if (n == '*' && ++i < str.length()) {
                            char n2 = str.charAt(i);
                            if (n2 == '/') {
                                b = false;
                            }
                        }
                    }
                } else {
                    sb.append(t);
                }
            } else {
                sb.append(c);
            }
        }
        return new String(sb);
    }


    /**
     * 读取文件
     *
     * @param filePath
     * @return
     */
    public static String readFile(String filePath) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(new File(filePath)));
        StringBuffer sb = new StringBuffer();
        while (bf.ready()) {
            String line = bf.readLine() + "\n";
            sb.append(line);
        }
        bf.close();
        return new String(sb);
    }


}
