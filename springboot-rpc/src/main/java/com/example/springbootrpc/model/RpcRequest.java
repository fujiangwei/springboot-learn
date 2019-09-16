package com.example.springbootrpc.model;

import lombok.Data;

/**
 * descripiton: Rpc请求对象
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/8/10
 * @time: 17:32
 * @modifier:
 * @since:
 */
@Data
public class RpcRequest {

    /**
     * 请求id
     */
    private String requestId;

    /**
     * 类名
     */
    private String className;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 参数类型
     */
    private Class<?>[] parameterTypes;

    /**
     * 参数
     */
    private Object[] parameters;
}
