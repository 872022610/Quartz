package com.qqw.demo.quartz.controller;

import com.qqw.demo.quartz.model.JobTaskModel;
import com.qqw.demo.quartz.service.JobService;
import com.qqw.demo.quartz.utils.CommonUtil;
import com.qqw.demo.quartz.common.BaseController;
import com.qqw.demo.quartz.utils.JsonPluginsUtil;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *  Quartz相关接口
 *
 * @author quanqiwei
 * @date 2019年8月5日 22:02:22
 */
@RestController
public class QuartzController extends BaseController{
    public Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private JobService jobService;

    /**
     * 指定对应的定时任务并开启
     * @param map 指定的定时任务相关信息
     * @author quanqiwei
     * @date 2019年8月5日 22:02:32
     * @return
     */
    @PostMapping(value = "/quartzTaskOpen")
    public String quartzTaskOpen(@RequestBody Map map){
        Class taskClass;
        try {
            //根据类名获取class，注意className比如包括系统路径，不能是单独的一个类名，不然会出现classNotFound的异常
            taskClass = Class.forName(map.get("jobClass").toString());
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            return failure(1,"未找到对应的class");
        }

        //jobDetail 运行的job
        JobDetail jobDetail =
                //根据类名获取对象
                JobBuilder.newJob(taskClass)
                        //设置任务名
                        .withIdentity(map.get("jobName").toString(), map.get("jobGroupName").toString())
                        //创建任务
                        .build();

        // 触发器类
        Trigger trigger = TriggerBuilder.newTrigger()
                //设置触发器名
                .withIdentity(map.get("triggerName").toString(), map.get("triggerGroupName").toString())
                .startNow()
                //设置触发时间
                .withSchedule(CronScheduleBuilder.cronSchedule(map.get("triggerCorn").toString()))
                .build();

        try {
            // 执行任务
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.error("出现异常");
        }
        return success();
    }

    /**
     * 关闭指定任务
     * @param map 指定的定时任务相关信息
     * @author quanqiwei
     * @date 2019年8月5日 22:02:32
     * @return
     */
    @PostMapping(value = "/quartzTaskClose")
    public String quartzTaskClose(@RequestBody Map map){
        Class taskClass;
        try {
            //根据类名获取class，注意className比如包括系统路径，不能是单独的一个类名，不然会出现classNotFound的异常
            taskClass = Class.forName(map.get("className").toString());
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            return failure(1,"未找到对应的class");
        }

        //jobDetail 运行的job
        JobDetail jobDetail =
                //根据class获取对象
                JobBuilder.newJob(taskClass)
                        //设置任务名
                        .withIdentity(map.get("jobName").toString(), map.get("jobGroupName").toString())
                        //创建任务
                        .build();

        try {
            //停止指定的job
            scheduler.pauseJob(jobDetail.getKey());
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.error("出现异常");
            return failure("1","停止指定的job失败");
        }
        return success();
    }

    /**
     * 初始化加载所有的定时任务
     * @author quanqiwei
     * @date 2019年8月5日 22:02:32
     * @return
     */
    @PostMapping(value = "/initQuartzTask")
    public String initQuartzTask(){

        //得到所有待执行的job列表
        List<JobTaskModel> jobTaskModelList =jobService.getJobTaskModelList();
        //设置执行下标，用于判断几个任务启动成功
        int indexFlag =  0;

        logger.info("定时任务组准备启动，本次一共需要启动"+ jobTaskModelList.size() + "个定时任务");

        //循环启动任务
        for (JobTaskModel elementJobTaskModel : jobTaskModelList) {

            try {
                Class taskClass;
                //根据类名获取class，注意className比如包括系统路径，不能是单独的一个类名，不然会出现classNotFound的异常
                taskClass = Class.forName(elementJobTaskModel.getJobClass());

                //jobDetail 运行的job
                JobDetail jobDetail =
                        //根据类名获取对象
                        JobBuilder.newJob(taskClass)
                                //设置任务名
                                .withIdentity(elementJobTaskModel.getJobName(), elementJobTaskModel.getJobGroupName())
                                //创建任务
                                .build();
                JobDataMap jobDataMap = jobDetail.getJobDataMap();
                //设置参数
                CommonUtil.setJobDataMap(jobDataMap,elementJobTaskModel.getParameterMap());

                // 触发器类
                Trigger trigger = TriggerBuilder.newTrigger()
                        //设置触发器名
                        .withIdentity(elementJobTaskModel.getTriggerName(), elementJobTaskModel.getTriggerGroupName())
                        .startNow()
                        //设置触发时间
                        .withSchedule(CronScheduleBuilder.cronSchedule(elementJobTaskModel.getTriggerCorn()))
                        .build();

                // 执行任务
                scheduler.scheduleJob(jobDetail, trigger);

            }catch (Exception e){
                //计算启动失败的job数量
                indexFlag++;
                e.printStackTrace();
                continue;
            }

        }
        logger.info("所有定时任务均已启动，其中"+ indexFlag + "个定时任务启动失败");
        return success("所有定时任务均已启动，其中"+ indexFlag + "个定时任务启动失败");
    }

    /**
     * 获取当前scheduler中所有正在运行的任务
     * @author quanqiwei
     * @date 2019年8月5日 22:02:32
     * @return
     */
    @PostMapping(value = "/getAllQuartzTask")
    public String getAllQuartzTask(){

        List<JobTaskModel> jobTaskModelList = new ArrayList<JobTaskModel>();
        List<String> list;

        try {
            //得到调度器中所有的jobGroupName
            list = scheduler.getJobGroupNames();

            //遍历jobGroupName
            for(String groupName : list){

                //根据jobGroupName获取对应的所有的jobKey集合
                Set<JobKey> jobKeySet = scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName));

                //遍历得到的jobKey集合，并将其封装类放入jobTaskModelList中
                for (JobKey jobKey : jobKeySet) {
                    JobTaskModel elementJobTaskModel = new JobTaskModel();
                    elementJobTaskModel.setJobGroupName(jobKey.getGroup());
                    elementJobTaskModel.setJobName(jobKey.getName());
                    jobTaskModelList.add(elementJobTaskModel);
                }

            }

        }catch (Exception e){
            e.printStackTrace();
            logger.error("从调度器获取所有的定时任务出错");
            return failure("1","失败");
        }
        return success(JsonPluginsUtil.beanListToJson(jobTaskModelList));
    }

    @PostMapping(value = "/demo")
    public String demo(){
        return  success();
    }
}
