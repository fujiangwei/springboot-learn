package com.example.springbootrpc.model;

import lombok.Data;

/**
 * descripiton: Rpc请求响应体
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/8/10
 * @time: 17:32
 * @modifier:
 * @since:
 */
@Data
public class RpcResponse {
    /**
     * 请求id
     */
    private String requestId;

    /**
     * 错误信息
     */
    private String error;

    /**
     * 结果
     */
    private Object result;

    public boolean isError() {
        return error != null;
    }
}
