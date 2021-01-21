package com.example.springbootlistener.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * CommandLineRunner ApplicationRunner之后(未设置order)被调用，设置order按order顺序调用
 * @author
 * @date
 */
@Slf4j
@Component
@Order(2)
public class MyCommandRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("MyCommandRunner run ..." + args.toString());
    }
}