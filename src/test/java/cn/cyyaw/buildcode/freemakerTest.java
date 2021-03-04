package cn.cyyaw.buildcode;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class freemakerTest {


    /**
     * 字符串模板
     */
    @Test
    public void test01() throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        String templateContent = "欢迎：${name}";
        stringLoader.putTemplate("myTemplate", templateContent);
        cfg.setTemplateLoader(stringLoader);
        Template template = cfg.getTemplate("myTemplate", "utf-8");
        Map root = new HashMap();
        root.put("name", "javaboy2012");
        StringWriter writer = new StringWriter();
        template.process(root, writer);
        System.out.println(writer.toString());
    }

    /**
     * list 测试
     */
    @Test
    public void test02() throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        String templateContent = "<#list animals as being>${being.name}${being.price}</#list>";
        stringLoader.putTemplate("myTemplate", templateContent);
        cfg.setTemplateLoader(stringLoader);
        Template template = cfg.getTemplate("myTemplate", "utf-8");

        ArrayList<String> list = new ArrayList<String>();


        Map root = new HashMap();
        root.put("name", "javaboy2012");
        root.put("animals", list);
        StringWriter writer = new StringWriter();
        template.process(root, writer);
        System.out.println(writer.toString());
    }
}
