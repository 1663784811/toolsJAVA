package cn.cyyaw.code.service;

import cn.cyyaw.code.table.entity.CPageComponents;
import cn.cyyaw.jpa.BaseTableService;

import java.util.List;

public interface CPageComponentsService extends BaseTableService<CPageComponents, Integer> {


    List<CPageComponents> findByExample(CPageComponents cPageComponents);
}
