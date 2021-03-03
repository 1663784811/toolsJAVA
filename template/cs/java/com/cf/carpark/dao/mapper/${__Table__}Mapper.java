package com.cf.carpark.dao.mapper;


import java.util.List;

public interface ${__Table__}Mapper {

    int countByExample(${__Table__}Example example);

    int deleteByExample(${__Table__}Example example);

    int deleteByPrimaryKey(String id);

    int insert(${__Table__} record);

    int insertSelective(${__Table__} record);

    List<${__Table__}> selectByExample(${__Table__}Example example);

    ${__Table__} selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ${__Table__} record, @Param("example") ${__Table__}Example example);

    int updateByExample(@Param("record") ${__Table__} record, @Param("example") ${__Table__}Example example);

    int updateByPrimaryKeySelective(${__Table__} record);

    int updateByPrimaryKey(${__Table__} record);
}