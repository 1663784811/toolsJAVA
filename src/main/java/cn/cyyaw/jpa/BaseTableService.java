package cn.cyyaw.jpa;

import cn.cyyaw.util.entity.SelectModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface BaseTableService<T, D> {


    List<T> findAll(String jsonStr, SelectModel selectModel);

    Page<T> findPage(String jsonStr, PageRequest pageRequest);

    T findId(D id);

    T add(T t);

    T update(T t);

    void del(D[] idArr);


}
