package cn.cyyaw.util.tools;


import cn.cyyaw.util.entity.SelectModel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class JpaUtils {


    /**
     * 获取PageRequest  =====     分页
     *
     * @param model
     * @return
     */
    public static PageRequest getPageRequest(final SelectModel model) {
        PageRequest pageRequest;
        Sort sort = getSort(model);
        if (null != sort) {
            pageRequest = PageRequest.of(model.getPage() - 1, model.getSize(), sort);
        } else {
            pageRequest = PageRequest.of(model.getPage() - 1, model.getSize());
        }
        return pageRequest;
    }


    /**
     * 获取Sort  ========  排序
     *
     * @param model
     * @return
     */
    public static Sort getSort(final  SelectModel model) {
        String sortStr = model.getSort();
        Sort sort = null;
        if (null != sortStr) {
            sortStr = sortStr.toLowerCase();
            String[] sortArr = sortStr.split(",");
            // 判断是否有这个字段
            for (int i = 0; i < sortArr.length; i++) {
                String[] s = sortArr[i].split("_");
                if (s.length == 2) {
                    if (s[1].equals("desc")) {
                        if (null == sort) {
                            sort = new Sort(Sort.Direction.DESC, s[0]);
                        } else {
                            sort.and(new Sort(Sort.Direction.DESC, s[0]));
                        }
                    } else {
                        if (null == sort) {
                            sort = new Sort(Sort.Direction.ASC, s[0]);
                        } else {
                            sort.and(new Sort(Sort.Direction.ASC, s[0]));
                        }
                    }
                }
            }
        }
        return sort;
    }


}
