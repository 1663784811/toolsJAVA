package cn.cyyaw.util.http;

import cn.cyyaw.util.tools.HttpClientUtil;

public class RquestTest implements Runnable {

    private String name = "";

    public RquestTest(String name) {
        this.name = name;
    }

    @Override
    public void run() {


        for (int i = 0; i < 100; i++) {

            String s = HttpClientUtil.doGet("http://j.cyyaw.cn/wx/getPhoneRecharge");

            System.out.println("线程：" + this.name + "请求第" + i + "次：" + s);
        }


    }
}
