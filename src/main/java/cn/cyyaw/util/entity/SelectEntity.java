package cn.cyyaw.util.entity;

public class SelectEntity implements SelectModel {
    /**
     * 页码
     */
    private int page = 1;
    /**
     * 大小
     */
    private int size = 30;
    /**
     * 排序   sort格式：    字段_desc,字段_asc
     */
    private String sort;
    /**
     * 排序
     * 0 默认 ASC
     * 1 倒序 DESC
     */
    private int line = 0;



    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }
}
