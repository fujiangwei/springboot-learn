package com.example.springbootscheduled.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * descripiton: Quartz任务类
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/2/17
 * @time: 21:14
 * @modifier:
 * @since:
 */
public class TestQuartz extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(Thread.currentThread().getName() + " quartz task " + new Date());
    }
}
