package com.example.springbootrpc.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * descripiton:
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/8/3
 * @time: 0:54
 * @modifier:
 * @since:
 */
@Component
@Order(2)
public class MyAppRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("i am MyAppRunner running..." + args.getOptionValues("1"));
    }
}
