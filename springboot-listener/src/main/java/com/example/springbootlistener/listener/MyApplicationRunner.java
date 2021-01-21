package com.example.springbootlistener.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * ApplicationRunner 容器完成后被调用
 * @author
 * @date
 */
@Slf4j
@Component
@Order(4)
public class MyApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("MyApplicationRunner run ..." + args.toString());
    }
}