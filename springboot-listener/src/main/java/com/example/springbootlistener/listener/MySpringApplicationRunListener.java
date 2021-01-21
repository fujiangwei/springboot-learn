package com.example.springbootlistener.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Springboot启动过程中多次被调用,需要注意：
 * 任何一个SpringApplicationRunListener实现类的构造方法都需要有两个构造参数，
 * 一个参数的类型就是我们的org.springframework.boot.SpringApplication,
 * 另外一个参数就是args参数列表的String[]:
 *
 * @author
 * @date
 */
@Slf4j
public class MySpringApplicationRunListener implements SpringApplicationRunListener {
    private final SpringApplication application;
    private final String[] args;

    public MySpringApplicationRunListener(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
    }

    @Override
    public void starting() {
        log.info("MySpringApplicationRunListener starting ... ");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        log.info("MySpringApplicationRunListener environmentPrepared ... ");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        log.info("MySpringApplicationRunListener contextPrepared ... ");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        log.info("MySpringApplicationRunListener contextLoaded ... ");
    }

}