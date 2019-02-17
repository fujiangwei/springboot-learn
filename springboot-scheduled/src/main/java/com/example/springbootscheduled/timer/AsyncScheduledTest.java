package com.example.springbootscheduled.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/2/17
 * @time: 20:10
 * @modifier:
 * @since:
 */
@Component
public class AsyncScheduledTest {

    Logger log = LoggerFactory.getLogger(AsyncScheduledTest.class);

    //每隔5s
    @Async
    @Scheduled(cron = "0/5 * * * * *")
    public void scheduled() {
        log.info(Thread.currentThread().getName() + "===AsyncScheduledTest使用cron {}", new Date());
    }

    //定义一个按一定频率（此处为5秒）执行的定时任务
    @Scheduled(fixedRate = 5000)
    public void scheduled1() {
        log.info(Thread.currentThread().getName() + "===AsyncScheduledTest使用fixedRate{}", new Date());
    }

    //定义一个5s后按一定频率（此处为5秒）执行的定时任务
    @Scheduled(fixedDelay = 5000, initialDelay = 5000)
    public void scheduled2() {
        log.info(Thread.currentThread().getName() + "===AsyncScheduledTest使用fixedDelay{}", new Date());
    }

}
