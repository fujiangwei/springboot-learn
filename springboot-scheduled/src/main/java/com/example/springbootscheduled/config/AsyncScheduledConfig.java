package com.example.springbootscheduled.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * descripiton: 多线程模式
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/2/17
 * @time: 20:34
 * @modifier:
 * @since:
 */
@Configuration
@EnableAsync
public class AsyncScheduledConfig {

    //线程池配置参数（可通过配置文件读取）
    //核心线程池数量
    private int corePoolSize = 10;
    //最大线程池数量
    private int maxPoolSize = 200;
    //队列容量
    private int queueCapacity = 10;

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);

        executor.initialize();

        return executor;
    }
}
