package com.qqw.demo.quartz.mapper;

import com.qqw.demo.quartz.entity.DemoTable;

public interface DemoTableMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DemoTable record);

    int insertSelective(DemoTable record);

    DemoTable selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DemoTable record);

    int updateByPrimaryKey(DemoTable record);
}