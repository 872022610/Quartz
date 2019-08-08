package com.qqw.demo.quartz.job;

import com.qqw.demo.quartz.entity.DemoTable;
import com.qqw.demo.quartz.mapper.DemoTableMapper;
import com.qqw.demo.quartz.utils.JsonPluginsUtil;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;


/**
 * demo for quartz JobDemo
 *
 * @author quanqiwei
 * @date 2019年8月4日 16:16:51
 */

@Component
public class Job4Demo1 extends QuartzJobBean {

    @Autowired
    private DemoTableMapper demoTableMapper;

    @Override
    public void executeInternal(JobExecutionContext jobExecutionContext){

        System.out.println("开始执行job1");

        //获取外部参数
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        System.out.println("job1参数列表" + JsonPluginsUtil.mapToString(jobDataMap));

        DemoTable insertDemoTable = new DemoTable();
        insertDemoTable.setCreateTime(new Date());
        insertDemoTable.setJobClassName("Job4Demo1");
        insertDemoTable.setUuid(UUID.randomUUID().toString());
        demoTableMapper.insertSelective(insertDemoTable);

    }
}
