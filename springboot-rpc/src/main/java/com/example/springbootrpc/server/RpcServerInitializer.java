package com.example.springbootrpc.server;

import com.example.springbootrpc.channelhandel.RpcDecoder;
import com.example.springbootrpc.channelhandel.RpcEncoder;
import com.example.springbootrpc.model.RpcRequest;
import com.example.springbootrpc.model.RpcResponse;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.HashMap;
import java.util.Map;


/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/8/14
 * @time: 0:04
 * @modifier:
 * @since:
 */
public class RpcServerInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * 保存注册服务（服务接口名称：服务接口对象）
     */
    private Map<String, Object> handleMap = new HashMap<>();

    public RpcServerInitializer(Map<String, Object> handleMap) {
        this.handleMap = handleMap;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new RpcEncoder(RpcResponse.class))
                .addLast(new LengthFieldBasedFrameDecoder(65536, 0, 4, 0, 0))
                .addLast(new RpcDecoder(RpcRequest.class))
                .addLast(new RpcServerHandler(this.handleMap));
    }
}
