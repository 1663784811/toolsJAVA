package cn.cyyaw.util.tools;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

import java.util.Map;

public class HttpUtil {


    /**
     * GET
     *
     * @param url
     * @param headerMap
     * @param queryMap
     * @return
     */
    public static String get(String url, Map<String, String> headerMap, Map<String, String> queryMap) {
        HttpRequest httpRequest = HttpRequest.get(url);
        return request(httpRequest, headerMap, queryMap);
    }

    /**
     * Post
     *
     * @param url
     * @param headerMap
     * @param queryMap
     * @return
     */
    public static String post(String url, Map<String, String> headerMap, Map<String, String> queryMap) {
        HttpRequest post = HttpRequest.post(url);
        return request(post, headerMap, queryMap);
    }

    /**
     * @param httpRequest httpRequest
     * @param headerMap   请求头
     * @param queryMap    参数
     * @return String
     */
    public static String request(HttpRequest httpRequest, Map<String, String> headerMap, Map<String, String> queryMap) {
        if (null != headerMap) {
            httpRequest.header(headerMap);
        }
        if (null != queryMap) {
            httpRequest.query(queryMap);
        }
        HttpResponse response = httpRequest.send();
        return response.body();
    }
}
