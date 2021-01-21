package com.example.springbootlistener;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author
 * @date
 */
@SpringBootApplication
public class SpringbootListenerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringbootListenerApplication.class, args);
    }

    /**
     * 向Spring中注入一个线程池对象供其它组件使用
     *
     * @return
     */
    @Bean(name = "applicationEventMulticasterExecutor")
    public ThreadPoolTaskExecutor asyncServiceExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(10);
        pool.setMaxPoolSize(20);
        pool.setQueueCapacity(50);
        pool.initialize();

        return pool;
    }

    @Bean("otherExecutor")
    public ThreadPoolTaskExecutor asyncServiceExecutor2() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(10);
        pool.setMaxPoolSize(20);
        pool.setQueueCapacity(50);
        pool.initialize();

        return pool;
    }

    /**
     * 向Spring中注入一个SimpleApplicationEventMulticaster，同时将前面配置的线程池对象
     * 交给这个监听器管理器对象使用，在源码中这个Bean的名字是固定的applicationEventMulticaster
     * AbstractApplicationContext中refresh方法中调用的initApplicationEventMulticaster方法： if (beanFactory.containsLocalBean("applicationEventMulticaster"))
     *
     * @return
     */
    @Bean("applicationEventMulticaster")
    @Autowired
    public SimpleApplicationEventMulticaster simpleApplicationEventMulticaster(
            BeanFactory beanFactory, @Qualifier("applicationEventMulticasterExecutor") ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        simpleApplicationEventMulticaster.setTaskExecutor(threadPoolTaskExecutor);

        return simpleApplicationEventMulticaster;
    }

}
