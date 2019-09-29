package cn.cyyaw.util.buildcode.config;


//=========   数据库配置
public final class DataConfig {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String PWD = "because";
    public static final String USER = "root";
    public static final String DATABASE = "weixin";
    public static final String URL = "jdbc:mysql://localhost/" + DATABASE + "?user=" + USER + "&password=" + PWD + "&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
}
