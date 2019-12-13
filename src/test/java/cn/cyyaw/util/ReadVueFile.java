package cn.cyyaw.util;

import java.io.*;

/**
 * 读取vue文件
 */
public class ReadVueFile {


    public static void main(String agrs[]) throws Exception {
        File file = new File("CField.vue");
        StringBuffer sb = readFile(file);
        String tag = getTag(sb); //获取标签
        String style = getStyle(sb); //获取样式
        String js = getJS(sb); //获取js 部分
        String importJs = getImportJs(js); //获取样式
        String components = vuejsData(js, "components: {"); //获取组件
        String data = vuejsData(vuejsData(js, "data() {"), "return {"); //获取数据
        String methods = vuejsData(js, "methods: {"); //获取方法
        String computed = vuejsData(js, "computed: {"); //获取计算属性
        String mounted = vuejsData(js, "mounted() {"); //获取初始化方法

        System.out.println(computed);
    }


    public static String vuejsData(final String js, String mark) {
        int index = js.indexOf(mark);
        if (index != -1) {
            int start = index + mark.length();
            int end = start + 1;
            int m = 1;
            while (m > 0 && js.length() >= end) {
                char c = js.charAt(end);
                if (c == '}') {
                    m--;
                } else if (c == '{') {
                    m++;
                }
                end++;
            }
            return js.substring(start, end - 1);
        }
        return "";
    }


    /**
     * 获取导入的js部分
     */
    public static String getImportJs(final String str) {
        String reStr = "";
        int index = str.indexOf("import");
        while (index != -1) {
            int end = str.indexOf("\n", index);
            end = end == -1 ? str.length() : end;


            reStr += "\n\r" + str.substring(index, end);
            index = str.indexOf("import", end);
        }
        return reStr;
    }


    /**
     * 获取js 部分代码
     */
    public static String getJS(final StringBuffer sb) {
        String tag = "<script>";
        int start = sb.indexOf(tag);
        int end = sb.lastIndexOf("</script>");
        int s = start + tag.length();
        if (start != -1 && end != -1 && end - s > 0) {
            return sb.substring(s, end);
        }
        return "";
    }


    /**
     * 获取样式部分内容
     */
    public static String getStyle(final StringBuffer sb) {
        int styleindex = sb.indexOf("<style");
        if (styleindex != -1) {
            int sindex = sb.indexOf(">", styleindex);
            int endindex = sb.lastIndexOf("</style>");
            if (sindex != -1 && endindex != -1 && endindex - sindex > 1) {
                return sb.substring(sindex + 1, endindex);
            }
        }
        return "";
    }


    /**
     * 获取标签部分内容
     */
    public static String getTag(final StringBuffer sb) {
        String tag = "<template>";
        int start = sb.indexOf(tag);
        int end = sb.lastIndexOf("</template>");
        int s = start + tag.length();
        if (start != -1 && end != -1 && end - s > 0) {
            return sb.substring(s, end);
        }
        return "";
    }

    /**
     * 读取文本文件内容
     */
    public static StringBuffer readFile(final File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(new BufferedInputStream(fis));
        int index = 0;
        StringBuffer sb = new StringBuffer();
        while (-1 != (index = isr.read())) {
            sb.append((char) index);
        }
        isr.close();
        fis.close();
        return sb;
    }


}
