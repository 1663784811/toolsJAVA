package cn.cyyaw.buildcode;

import org.junit.Test;

import java.io.File;
import java.io.IOException;


/**
 * 文件操作
 */
public class FileTest {

    @Test
    public void test01() throws IOException {
        File file = new File("I:\\ab\\a.ftl.txt");
        System.out.println("获取绝对路径：" + file.getAbsolutePath());
        System.out.println("获取文件名：" + file.getName());
        System.out.println("获取文件名：" + file.getPath());
        System.out.println("路径：" + file.isFile());
        System.out.println("路径：" + file.isDirectory());
//        CreateCode createCode = new CreateCode();
//        System.out.println("目录" + createCode.getCatalog(file));
    }


}
