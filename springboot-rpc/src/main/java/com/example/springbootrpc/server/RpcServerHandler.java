package com.example.springbootrpc.server;

import com.example.springbootrpc.model.RpcRequest;
import com.example.springbootrpc.model.RpcResponse;
import io.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.reflect.FastClass;

import java.util.Map;

/**
 * descripiton: RpcServer处理器
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/8/10
 * @time: 17:30
 * @modifier:
 * @since:
 */
public class RpcServerHandler extends SimpleChannelInboundHandler<RpcRequest> {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(RpcServerHandler.class);

    /**
     * 保存注册服务（服务接口名称：服务接口对象）
     */
    private Map<String, Object> handleMap;

    public RpcServerHandler(Map<String, Object> handleMap) {
        this.handleMap = handleMap;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext chc, RpcRequest rpcRequest) throws Exception {
        RpcServer.submit(new Runnable() {
            @Override
            public void run() {
                
                String requestId = rpcRequest.getRequestId();
                logger.debug("Receive request {}", requestId);
                RpcResponse response = new RpcResponse();
                response.setRequestId(requestId);
                try{
                    Object result = handle(rpcRequest);
                    response.setResult(result);
                } catch (Exception e) {
                    logger.error("RPC Server handle request error,", e);
                    response.setError(e.getMessage());
                }
                chc.writeAndFlush(response).addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        logger.debug("Send response for request {}", requestId);
                    }
                });
            }
        });
    }

    /**
     * @descripiton: 处理请求
     * @author: kinson
     * @date: 2019/8/13 22:45
     * @param rpcRequest
     * @exception：
     * @modifier：
     * @return：java.lang.Object
     */
    private Object handle(RpcRequest rpcRequest) throws Exception {

        String className = rpcRequest.getClassName();
        Object serviceBean = handleMap.get(className);

        Class<?> serviceClass = serviceBean.getClass();
        String methodName = rpcRequest.getMethodName();
        Object[] parameters = rpcRequest.getParameters();
        Class<?>[] parameterTypes = rpcRequest.getParameterTypes();

        logger.debug("Service name is {}, and methodName is {}", serviceClass.getName(), methodName);
        for (Class<?> c : parameterTypes) {
            logger.debug(c.getName());
        }

        for (Object parameter : parameters) {
            logger.debug(parameter.toString());
        }

//        Method method = serviceClass.getMethod(methodName, parameterTypes);
//        method.setAccessible(Boolean.TRUE);
//        return method.invoke(serviceBean, parameters);

        FastClass serviceFastClass = FastClass.create(serviceClass);
        int methodIndex = serviceFastClass.getIndex(methodName, parameterTypes);
        return serviceFastClass.invoke(methodIndex, serviceBean, parameters);
    }

    /**
     * 异常捕获
     *
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) throws Exception {
        Channel channel = ctx.channel();
        logger.error("server caught exception", e);
        ctx.close().sync();
    }

}
