package com.example.springbootrpc.client;

import com.example.springbootrpc.model.RpcRequest;
import com.example.springbootrpc.model.RpcResponse;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketAddress;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/8/14
 * @time: 0:04
 * @modifier:
 * @since:
 */
public class RpcClientHandler extends SimpleChannelInboundHandler<RpcResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RpcClientHandler.class);

    private SocketAddress remotePeer;

    private volatile Channel channel;

    private ConcurrentHashMap<String, RpcFuture> pendingRPCMap = new ConcurrentHashMap();

    public Channel getChannel() {
        return channel;
    }

    public SocketAddress getRemotePeer() {
        return remotePeer;
    }

    public void close() {
        channel.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    public RpcFuture sendRequest(RpcRequest request) {
        final CountDownLatch latch = new CountDownLatch(1);
        RpcFuture future = new RpcFuture(request);
        pendingRPCMap.put(request.getRequestId(), future);

        channel.writeAndFlush(request).addListener((channelFuture) -> {
            latch.countDown();
        });
        try {
            //后面的线程阻塞在当前线程处理完成之后
            latch.await();
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }
        return future;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcResponse rpcResponse) throws Exception {
        String requestId = rpcResponse.getRequestId();
        RpcFuture future = pendingRPCMap.get(requestId);
        if (null != future) {
            pendingRPCMap.remove(requestId);
            future.done(rpcResponse);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext chc) throws Exception {
        super.channelActive(chc);
        this.remotePeer = this.channel.remoteAddress();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext chc) throws Exception {
        super.channelRegistered(chc);
        this.channel = chc.channel();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error("client caught exception", cause);
        ctx.close();
    }
}
