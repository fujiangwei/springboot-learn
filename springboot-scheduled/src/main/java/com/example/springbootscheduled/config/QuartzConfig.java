package com.example.springbootscheduled.config;

import com.example.springbootscheduled.quartz.TestQuartz;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/2/17
 * @time: 20:52
 * @modifier:
 * @since:
 */
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail testQuartzDetail() {
        return JobBuilder.newJob(TestQuartz.class)
                .withIdentity("testQuartz")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger testQuartzTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10) //设置时间周期单位秒
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(testQuartzDetail())
                .withIdentity("testQuartzTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
