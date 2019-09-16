package com.example.springbootrpc.client.proxy;

import com.example.springbootrpc.client.RpcFuture;

/**
 * descripiton:
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/8/13
 * @time: 23:06
 * @modifier:
 * @since:
 */
public interface IAsyncObjectProxy {

    RpcFuture call(String funcName, Object... args);
}
