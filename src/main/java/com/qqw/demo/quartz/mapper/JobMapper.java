package com.qqw.demo.quartz.mapper;

import com.qqw.demo.quartz.entity.Job;

public interface JobMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Job record);

    int insertSelective(Job record);

    Job selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Job record);

    int updateByPrimaryKey(Job record);
}