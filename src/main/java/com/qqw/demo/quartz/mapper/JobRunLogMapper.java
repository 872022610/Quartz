package com.qqw.demo.quartz.mapper;

import com.qqw.demo.quartz.entity.JobRunLog;

public interface JobRunLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JobRunLog record);

    int insertSelective(JobRunLog record);

    JobRunLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JobRunLog record);

    int updateByPrimaryKey(JobRunLog record);
}