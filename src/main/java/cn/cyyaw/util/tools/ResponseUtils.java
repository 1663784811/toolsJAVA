package cn.cyyaw.util.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class ResponseUtils {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseUtils.class);

    /**
     * TYPE_TEXT_HTML
     */
    private static final String TYPE_TEXT_HTML = "text/html";

    /**
     * TYPE_APPLICATION_JSON
     */
    private static final String TYPE_APPLICATION_JSON = "application/json";

    /**
     * 隐藏实例化
     */
    private ResponseUtils() {

    }

    /**
     * 生成对应的JSON，无过滤条件
     *
     * @param response the response
     * @param obj      the obj
     */
    public static void doResponseJson(final HttpServletResponse response, final Object obj) {
        doResponseJson(response, obj, null);
    }

    /**
     * 生成对应的JSON，有过滤条件
     *
     * @param response the response
     * @param obj      the obj
     * @param filter   the filter
     */
    public static void doResponseJson(final HttpServletResponse response, final Object obj, final SimplePropertyPreFilter filter) {
        response.setContentType("application/json; charset=utf-8");
        try {
            if (filter != null) {
                JsonUtil.writeJSONStringTo(obj, response.getWriter(), filter, SerializerFeature.WriteDateUseDateFormat);
                if (LOGGER.isDebugEnabled()) {
                    String strJson = JSON.toJSONString(obj, filter, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.PrettyFormat);
                    LOGGER.debug("doReponseJson(HttpServletResponse, Object) - \n\n{}\n\n", strJson);
                }
            } else {
                JsonUtil.writeJSONString(response.getWriter(), obj, SerializerFeature.WriteDateUseDateFormat);
                if (LOGGER.isDebugEnabled()) {
                    String strJson = JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.PrettyFormat);
                    LOGGER.debug("doReponseJson(HttpServletResponse, Object) - \n\n{}\n\n", strJson);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.error("doReponseJson(HttpServletResponse, Object)", e);
            }
        }
    }



    public static void doResponseHTML(final HttpServletResponse response, String html){
        response.setContentType("text/html; charset=utf-8");
        try {
            response.getWriter().println(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}