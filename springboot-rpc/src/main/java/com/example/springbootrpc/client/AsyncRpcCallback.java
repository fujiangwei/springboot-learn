package com.example.springbootrpc.client;

/**
 * descripiton:
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/8/15
 * @time: 22:00
 * @modifier:
 * @since:
 */
public interface AsyncRpcCallback {

    void success(Object result);

    void fail(Exception e);
}
