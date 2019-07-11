package cn.cyyaw.util.tools;


import java.util.UUID;

/**
 * 字符串工具类
 */
public class WhyStringUtil {

    private static String string = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";

    public static String getRandomString(int length) {
        StringBuffer sb = new StringBuffer();
        int len = string.length();
        for (int i = 0; i < length; i++) {
            sb.append(string.charAt(getRandom(len - 1)));
        }
        return sb.toString();
    }

    private static int getRandom(int count) {
        return (int) Math.round(Math.random() * (count));
    }

    /**
     * 获取UUID
     *
     * @return
     */
    public static String getUUID() {
        String str = UUID.randomUUID().toString();
        return str.replace("-", "");
    }

}
