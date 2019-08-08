package com.qqw.demo.quartz.mapper;

import com.qqw.demo.quartz.entity.Parameter;

public interface ParameterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Parameter record);

    int insertSelective(Parameter record);

    Parameter selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Parameter record);

    int updateByPrimaryKey(Parameter record);
}