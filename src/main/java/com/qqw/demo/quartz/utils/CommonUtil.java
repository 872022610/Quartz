package com.qqw.demo.quartz.utils;

import com.qqw.demo.quartz.entity.Job;
import com.qqw.demo.quartz.entity.Parameter;
import com.qqw.demo.quartz.model.JobTaskModel;
import org.quartz.JobDataMap;

import java.util.*;

public class CommonUtil {
    /**
     * 根据查询出来的job列表以及parameter列表生成待执行的job列表
     *
     * @param jobList 查询出来的job列表
     * @param parameterList 查询出来的参数列表
     * @return
     */
    public static List<JobTaskModel> getJobTaskModelList(List<Job> jobList, List<Parameter> parameterList){

        List<JobTaskModel> jobTaskModelList = new ArrayList<JobTaskModel>();
        Date now = new Date();

        for (Job elementJob : jobList) {

            if(elementJob.getJobStartTime() != null && elementJob.getJobStartTime().after(now)){
                continue;
            }

            if(elementJob.getJobEndTime() != null && elementJob.getJobEndTime().before(now)){
                continue;
            }

            JobTaskModel elementJobTaskModel = new JobTaskModel();
            Map<String,Object> parameterMap = new HashMap<String,Object>();

            for (Parameter elementParameter : parameterList) {

                if(elementJob.getId().equals( elementParameter.getJobId())){

                    elementJobTaskModel.setJobClass(elementJob.getJobClassPackagePath() + elementJob.getJobClassName());
                    elementJobTaskModel.setJobName(elementJob.getJobName());
                    elementJobTaskModel.setJobGroupName(elementJob.getJobGroupName());
                    elementJobTaskModel.setTriggerName(elementJob.getTriggerName());
                    elementJobTaskModel.setTriggerGroupName(elementJob.getTriggerGroupName());
                    elementJobTaskModel.setTriggerCorn(elementJob.getTriggerCorn());
                    parameterMap.put(elementParameter.getParameterName(), elementParameter.getParameterValue());

                }

            }

            elementJobTaskModel.setParameterMap(parameterMap);

            jobTaskModelList.add(elementJobTaskModel);

        }

        return jobTaskModelList;

    }

    public static void setJobDataMap(JobDataMap jobDataMap, Map<String,Object> jobParameterMap){
        for (String key : jobParameterMap.keySet()) {
            jobDataMap.put(key,jobParameterMap.get(key));
        }

    }

}
