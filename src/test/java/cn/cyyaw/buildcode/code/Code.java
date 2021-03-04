package cn.cyyaw.buildcode.code;


import cn.cyyaw.buildcode.croe.code.CreateCode;
import org.junit.Test;

import java.io.IOException;

/**
 * 构建代码测试
 */
public class Code {


    /**
     * 生成文件测试
     *
     * @throws IOException
     */
    @Test
    public void test01() throws IOException {
        //===========    获取数据
        CreateCode createCode = new CreateCode();
        createCode.out();
    }


}
