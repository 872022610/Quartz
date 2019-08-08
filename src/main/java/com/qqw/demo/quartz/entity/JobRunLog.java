package com.qqw.demo.quartz.entity;

import java.util.Date;

public class JobRunLog {
    private Long id;

    private Long jobId;

    private Integer jobResultCode;

    private String jobResultMsg;

    private String jobResultData;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Integer getJobResultCode() {
        return jobResultCode;
    }

    public void setJobResultCode(Integer jobResultCode) {
        this.jobResultCode = jobResultCode;
    }

    public String getJobResultMsg() {
        return jobResultMsg;
    }

    public void setJobResultMsg(String jobResultMsg) {
        this.jobResultMsg = jobResultMsg == null ? null : jobResultMsg.trim();
    }

    public String getJobResultData() {
        return jobResultData;
    }

    public void setJobResultData(String jobResultData) {
        this.jobResultData = jobResultData == null ? null : jobResultData.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }
}