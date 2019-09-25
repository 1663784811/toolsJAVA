package cn.cyyaw.util.http;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import org.junit.Test;

public class T {


    @Test
    public void s() {


        HttpRequest httpRequest = HttpRequest.get("https://www.baidu.com/");
        HttpResponse response = httpRequest.send();

        System.out.println(response.body());


    }


}
