package com.qqw.demo.quartz.model;

import java.util.Map;

public class JobTaskModel {

    private String jobClass;

    private String jobName;

    private String jobGroupName;

    private String triggerName;

    private String triggerGroupName;

    private String triggerCorn;

    private Map<String,Object> parameterMap;

    public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroupName() {
        return jobGroupName;
    }

    public void setJobGroupName(String jobGroupName) {
        this.jobGroupName = jobGroupName;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTriggerGroupName() {
        return triggerGroupName;
    }

    public void setTriggerGroupName(String triggerGroupName) {
        this.triggerGroupName = triggerGroupName;
    }

    public String getTriggerCorn() {
        return triggerCorn;
    }

    public void setTriggerCorn(String triggerCorn) {
        this.triggerCorn = triggerCorn;
    }

    public Map<String,Object> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String,Object> parameterMap) {
        this.parameterMap = parameterMap;
    }
}
