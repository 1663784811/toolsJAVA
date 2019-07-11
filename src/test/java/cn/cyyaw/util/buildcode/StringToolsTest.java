package cn.cyyaw.util.buildcode;

import cn.cyyaw.util.buildcode.code.OperationTools;
import org.junit.Before;
import org.junit.Test;

/**
 * 字符串测试
 */
public class StringToolsTest {

    OperationTools operationTools;

    @Before
    public void testBefer() {
        operationTools = new OperationTools();
    }

    @Test
    public void test01() {
        System.out.println("=================================  去除特殊字符");
        String[] strArr = {
                "sfsdf-sdfsiodf-sdf特殊字符_dijf s=sfjsi",
                "sdfjis23er9fsj0特殊字符fs=f903_+93e4rfjof90sz_/sdf",
                "jsi s=isfu930i09-j特殊字符90u93特殊字符090sfus33wmsoie=s",
                "s",
                "ssfsdfs特殊字符dfsdfSDfikj特殊字符dssdikjfsdfjp;sfjisd",
                "",
                "-=-=-=-?>:>"
        };
        for (int i = 0; i < strArr.length; i++) {
            System.out.println("测试：" + strArr[i] + "\r\n返回：" + operationTools.delSpecial(strArr[i]) + "\r\n");
        }
    }

    @Test
    public void test02() {
        System.out.println("=================================   去除特殊字符，首字母大写");
        String[] strArr = {
                "sfsdf-sdfsiodf-sdf_dijf s=sfjsi",
                "sdfjis23er9fsj0fs=f903_+93e4rfjof90sz_/sdf",
                "jsi s=isfu930i09-j90u93090sfus33wmsoie=s",
                "s",
                "",
                "-=-=-=-?>:>"
        };
        for (int i = 0; i < strArr.length; i++) {
            System.out.println("测试：" + strArr[i] + "\r\n返回：" + operationTools.indexToUpperCase(strArr[i]) + "\r\n");
        }
    }

    @Test
    public void test03() {
        System.out.println("=================================   去除特殊字符，首字母小写");
        String[] strArr = {
                "sfsdf-sdfsiodf-sdf_dijf s=sfjsi",
                "sdfjis23er9fsj0fs=f903_+93e4rfjof90sz_/sdf",
                "jsi s=isfu930i09-j90u93090sfus33wmsoie=s",
                "s",
                "",
                "-=-=-=-?>:>"
        };
        for (int i = 0; i < strArr.length; i++) {
            System.out.println("测试：" + strArr[i] + "\r\n返回：" + operationTools.indexToLowerCase(strArr[i]) + "\r\n");
        }
    }

    @Test
    public void test04() {
        System.out.println("=================================   去除特殊字符，所有字母小写");
        String[] strArr = {
                "sfsdf-sdfsiodf-sdf_dijf s=sfjsi",
                "sdfjis23er9fsj0fs=f903_+93e4rfjof90sz_/sdf",
                "jsi s=isfu930i09-j90u93090sfus33wmsoie=s",
                "s",
                "",
                "-=-=-=-?>:>"
        };
        for (int i = 0; i < strArr.length; i++) {
            System.out.println("测试：" + strArr[i] + "\r\n返回：" + operationTools.allToLowerCase(strArr[i]) + "\r\n");
        }
    }

    @Test
    public void test05() {
        System.out.println("=================================   去除特殊字符，所有字母大写");
        String[] strArr = {
                "sfsdf-sdfsiodf-sdf_dijf s=sfjsi",
                "sdfjis23er9fsj0fs=f903_+93e4rfjof90sz_/sdf",
                "jsi s=isfu930i09-j90u93090sfus33wmsoie=s",
                "s",
                "",
                "-=-=-=-?>:>"
        };
        for (int i = 0; i < strArr.length; i++) {
            System.out.println("测试：" + strArr[i] + "\r\n返回：" + operationTools.allToUpperCase(strArr[i]) + "\r\n");
        }
    }
}
