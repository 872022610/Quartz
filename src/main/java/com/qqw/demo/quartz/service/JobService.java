package com.qqw.demo.quartz.service;

import com.qqw.demo.quartz.utils.CommonUtil;
import com.qqw.demo.quartz.entity.Job;
import com.qqw.demo.quartz.entity.Parameter;
import com.qqw.demo.quartz.mapper.BusJobMapper;
import com.qqw.demo.quartz.mapper.BusParameterMapper;
import com.qqw.demo.quartz.model.JobTaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private BusJobMapper busJobMapper;

    @Autowired
    private BusParameterMapper busParameterMapper;

    public List<JobTaskModel> getJobTaskModelList(){
        List<Job> jobList = busJobMapper.selectJobList();
        List<Parameter> parameterList = busParameterMapper.selectJobParameterList();
        return CommonUtil.getJobTaskModelList(jobList, parameterList);
    }

}
