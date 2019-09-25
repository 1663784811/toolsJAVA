package cn.cyyaw.jpa;

import cn.cyyaw.util.entity.SelectModel;
import cn.cyyaw.util.tools.JpaUtils;
import cn.cyyaw.util.tools.WhySpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;


public abstract class BaseService<T, D> implements BaseTableService<T, D> {
    public abstract BaseDao getBaseDao();

    public List<T> findAll(String jsonStr, SelectModel selectModel, String... selectWhereArr) {
        Sort sort = JpaUtils.getSort(selectModel);
        if (null != sort) {
            return getBaseDao().findAll(new WhySpecification<T>(jsonStr, selectWhereArr), sort);
        } else {
            return getBaseDao().findAll(new WhySpecification<T>(jsonStr, selectWhereArr));
        }
    }

    public Page<T> findPage(String jsonStr, PageRequest pageRequest, String... strings) {
        return getBaseDao().findAll(new WhySpecification<T>(jsonStr, strings), pageRequest);
    }

    public T findId(D d) {
        return (T) getBaseDao().findByid(d);
    }

    public T save(T t) {
        return (T) getBaseDao().save(t);
    }

    public void del(D[] ds) {
        getBaseDao().deleteInBatch(getBaseDao().findByidIsIn(ds));
    }

    public void del(D d) {
        getBaseDao().deleteById(d);
    }

}
