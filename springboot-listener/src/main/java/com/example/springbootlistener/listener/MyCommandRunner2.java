package com.example.springbootlistener.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author
 * @date
 */
@Slf4j
@Component
@Order(1)
public class MyCommandRunner2 implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("MyCommandRunner2 run ..." + args.toString());
    }
}