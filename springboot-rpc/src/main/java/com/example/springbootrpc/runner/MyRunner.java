package com.example.springbootrpc.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/8/2
 * @time: 23:52
 * @modifier:
 * @since:
 */
@Component
@Order(1)
public class MyRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("i am MyRunner running..." + args[3]);
    }
}

