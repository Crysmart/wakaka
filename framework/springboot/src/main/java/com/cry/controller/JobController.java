package com.cry.controller;

import com.cry.service.job.QuartzJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Set;

import static org.quartz.CronScheduleBuilder.cronSchedule;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    StdSchedulerFactory stdSchedulerFactory;

    @GetMapping("/jobKeys")
    public Set<JobKey> jobKeys() throws SchedulerException {
        Scheduler scheduler = stdSchedulerFactory.getScheduler();
        Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.anyJobGroup());
        return jobKeys;
    }

    @GetMapping("/triggerKeys")
    public Set<TriggerKey> triggerKeys() throws SchedulerException {
        Scheduler scheduler = stdSchedulerFactory.getScheduler();
        Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(GroupMatcher.anyGroup());
        return triggerKeys;
    }

    @GetMapping("/createJob")
    public JobDetail createJob(String name,String key,String value) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class)
                .withIdentity(name)
                .usingJobData(key + "JobBuilder", value + "JobBuilder")
                .storeDurably()
                .build();
        Scheduler scheduler = stdSchedulerFactory.getScheduler();
        if (!scheduler.isStarted()) {
            scheduler.start();
        }
        scheduler.addJob(jobDetail,false);
        return jobDetail;
    }

    @GetMapping("/schedulerShutDown")
    public Scheduler schedulerShutDown(String name,String key,String value) throws SchedulerException {
        Scheduler scheduler = stdSchedulerFactory.getScheduler();
        if (!scheduler.isShutdown()) {
            scheduler.shutdown(true);
        }
        return scheduler;
    }


    @GetMapping("/resume")
    public void resume(String name,String group) throws SchedulerException {
        Scheduler scheduler = stdSchedulerFactory.getScheduler();
        scheduler.resumeJob(new JobKey(name,group));
    }

    @GetMapping("/pause")
    public void pause(String name,String group) throws SchedulerException {
        Scheduler scheduler = stdSchedulerFactory.getScheduler();
        scheduler.pauseJob(new JobKey(name,group));
    }

    @GetMapping("/addTrigger")
    public Scheduler addTrigger(String triggerName,String jobName,String cron) throws SchedulerException {
        Scheduler scheduler = stdSchedulerFactory.getScheduler();

        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerName)
                .forJob(jobName)
                .withSchedule(cronSchedule(cron))
                .build();
        scheduler.scheduleJob(cronTrigger);
        return scheduler;
    }

    @GetMapping("/bind")
    public Date bind(String jobKey,String jobGroup,String triggerKey,String trigGroup) throws SchedulerException {
        Scheduler scheduler = stdSchedulerFactory.getScheduler();
        Trigger trigger = scheduler.getTrigger(new TriggerKey(triggerKey, trigGroup));
        JobDetail jobDetail = scheduler.getJobDetail(new JobKey(jobKey, jobGroup));
        Date date = scheduler.scheduleJob(jobDetail, trigger);
        return date;
    }

}
