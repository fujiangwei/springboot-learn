package com.example.springbootlog4j.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/9/15
 * @time: 12:29
 * @modifier:
 * @since:
 */
@RestController
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping(value = "log")
    public void test() {
        logger.info("123131313");
        logger.debug("123131313");
        logger.warn("123131313");
        logger.error("123131313");
    }
}
