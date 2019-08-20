package com.example.springbootrpc.service.impl;

import com.example.springbootrpc.annotation.RpcService;
import com.example.springbootrpc.service.HelloService;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/8/4
 * @time: 13:24
 * @modifier:
 * @since:
 */
@RpcService(HelloService.class)
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello() {
        return "hello";
    }

    @Override
    public String hello(String msg) {
        return "hello, " + msg;
    }
}
