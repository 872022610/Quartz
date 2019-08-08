package com.qqw.demo.quartz.mapper;

import com.qqw.demo.quartz.entity.Job;

import java.util.List;

public interface BusJobMapper {
    List<Job> selectJobList();
}