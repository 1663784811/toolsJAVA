package cn.cyyaw.util.json;

import cn.cyyaw.util.entity.User;
import cn.cyyaw.util.tools.JsonUtil;
import org.junit.Test;

public class JsonTest {


    @Test
    public void test01() {
        User user = new User();
        user.setAge(1);
        user.setName("sssssss");

        String s = JsonUtil.toJSONStringAllow(user, "");

        System.out.println("数据：" + s);

    }


}
