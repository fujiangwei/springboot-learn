package com.example.springbootlistener.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author
 * @date
 */
@Slf4j
@Component
@Order(3)
public class MyApplicationRunner2 implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("MyApplicationRunner2 run ..." + args.toString());
    }
}