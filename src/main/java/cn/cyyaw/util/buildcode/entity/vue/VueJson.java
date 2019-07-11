package cn.cyyaw.util.buildcode.entity.vue;

/**
 * vue JSON 文件实体
 */
public class VueJson {

    private String key;                 //键值
    private String title;               //名称
    private String type;                //列表类型
    private Integer length;             //长度
    private Boolean isRequire=false;          //是否必须
    private String regStr;              //正则表达式
    private String message;             // 提示
    private String controlType;         //控件类型
    private Integer max;                //最大值
    private Integer min;                //最小值
    private String format;              //格式化

    private Boolean isWhere=true;           //是否是查询条件
    private Boolean isShowColumn=true;      //是否显示列表

    private String javaWhere;           //java条件
    private String javaType;            //java类型

    public void setWhere(Boolean where) {
        isWhere = where;
    }

    public void setShowColumn(Boolean showColumn) {
        isShowColumn = showColumn;
    }

    public Boolean getWhere() {
        return isWhere;
    }

    public Boolean getShowColumn() {
        return isShowColumn;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public void setRequire(Boolean require) {
        isRequire = require;
    }

    public void setRegStr(String regStr) {
        this.regStr = regStr;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setControlType(String controlType) {
        this.controlType = controlType;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setJavaWhere(String javaWhere) {
        this.javaWhere = javaWhere;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public Integer getLength() {
        return length;
    }

    public Boolean getRequire() {
        return isRequire;
    }

    public String getRegStr() {
        return regStr;
    }

    public String getMessage() {
        return message;
    }

    public String getControlType() {
        return controlType;
    }

    public Integer getMax() {
        return max;
    }

    public Integer getMin() {
        return min;
    }

    public String getFormat() {
        return format;
    }

    public String getJavaWhere() {
        return javaWhere;
    }

    public String getJavaType() {
        return javaType;
    }
}
