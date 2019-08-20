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
@Order(2)
public class MyRunner2 implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("i am MyRunner2 running..." + args.length + "---" + args[0]);
    }
}

