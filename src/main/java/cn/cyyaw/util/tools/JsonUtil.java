package cn.cyyaw.util.tools;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.*;

import java.io.Writer;

public class JsonUtil extends JSON{


    /**
     * 将转换成的json写入http的返回中
     *
     * @param object   待转换的对象
     * @param writer   http的写入对象
     * @param filter   过滤器
     * @param features 特征库
     */
    public static final void writeJSONStringTo(final Object object, final Writer writer, final SerializeFilter filter, final SerializerFeature... features) {
        SerializeWriter out = new SerializeWriter(writer);

        try {
            JSONSerializer serializer = new JSONSerializer(out);
            for (SerializerFeature feature : features) {
                serializer.config(feature, true);
            }

            setFilter(serializer, filter);
            serializer.write(object);
        } finally {
            out.close();
        }
    }

    /**
     * 设置过滤器，强制某些字段不会转换
     *
     * @param serializer 转换构造器
     * @param filter     过滤器
     */
    private static void setFilter(final JSONSerializer serializer, final SerializeFilter filter) {
        if (filter == null) {
            return;
        }

        if (filter instanceof PropertyPreFilter) {
            serializer.getPropertyPreFilters().add((PropertyPreFilter) filter);
        }

        if (filter instanceof NameFilter) {
            serializer.getNameFilters().add((NameFilter) filter);
        }

        if (filter instanceof ValueFilter) {
            serializer.getValueFilters().add((ValueFilter) filter);
        }

        if (filter instanceof PropertyFilter) {
            serializer.getPropertyFilters().add((PropertyFilter) filter);
        }

        if (filter instanceof BeforeFilter) {
            serializer.getBeforeFilters().add((BeforeFilter) filter);
        }

        if (filter instanceof AfterFilter) {
            serializer.getAfterFilters().add((AfterFilter) filter);
        }
    }

}
