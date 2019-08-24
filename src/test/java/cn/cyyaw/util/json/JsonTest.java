package cn.cyyaw.util.json;

import cn.cyyaw.util.entity.User;
import cn.cyyaw.util.tools.JsonUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;

public class JsonTest {


    @Test
    public void test01() {
        User user = new User();
        user.setAge(1);
        user.setName("sssssss");

        String s = JsonUtil.toJSONStringFilter(user,"age");

        System.out.println("数据：" + s);

    }


}
