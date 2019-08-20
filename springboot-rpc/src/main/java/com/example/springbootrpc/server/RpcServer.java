package com.example.springbootrpc.server;

import com.example.springbootrpc.annotation.RpcService;
import com.example.springbootrpc.channelhandel.RpcDecoder;
import com.example.springbootrpc.channelhandel.RpcEncoder;
import com.example.springbootrpc.model.RpcRequest;
import com.example.springbootrpc.model.RpcResponse;
import com.example.springbootrpc.registry.ServiceRegistry;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/8/10
 * @time: 15:28
 * @modifier:
 * @since:
 */
//@SL4J //需安装lombok插件才能生效
public class RpcServer implements ApplicationContextAware, InitializingBean {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(RpcServer.class);

    /**
     * 服务ip
     */
    private String serverIp;

    /**
     * 服务端口
     */
    private int port;

    /**
     * 服务注册
     */
    private ServiceRegistry serviceRegistry;

    /**
     * 保存注册服务（服务接口名称：服务接口对象）
     */
    private Map<String, Object> handleMap = new HashMap<>();

    /**
     * 线程池
     */
    public static ThreadPoolExecutor poolExecutor;

    /**
     * EventLoopGroup是用来处理IO操作的多线程事件循环器
     *  负责接收客户端连接线程
     */
    private EventLoopGroup bossGroup;

    /**
     * 负责处理客户端i/o事件、task任务、监听任务组
     */
    private EventLoopGroup workerGroup;

    public RpcServer(String serverIp, int port) {
        this.serverIp = serverIp;
        this.port = port;
    }

    public RpcServer(String serverIp, int port, ServiceRegistry serviceRegistry) {
        this(serverIp, port);
        this.serviceRegistry = serviceRegistry;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        start();
    }

    /**
     * @descripiton: 启动
     * @author: kinson
     * @date: 2019/8/10 21:14
     * @exception：
     * @modifier：
     * @return：void
     */
    private void start() {
        if (null == bossGroup && null == workerGroup) {
            bossGroup = new NioEventLoopGroup();
            workerGroup = new NioEventLoopGroup();
            //启动 NIO 服务的辅助启动类
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(bossGroup, workerGroup)
                    //配置channel
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new RpcServerInitializer(handleMap))
            //BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务端请求线程全满时，用于临时存放已完成3次握手的请求的队列的最大长度
            .option(ChannelOption.SO_BACKLOG, 1024)
            //启用心跳保活机制
            .childOption(ChannelOption.SO_KEEPALIVE, true);

            try {
                //绑定服务端口监听
                
                Channel channel = bootstrap.bind(serverIp, port).sync().channel();
                String serverAddr = String.format("s%:d%", serverIp, port);
                logger.info("server run in {}", serverAddr);

                
                //服务注册
                if (null != serviceRegistry) {
                    serviceRegistry.registry(serverAddr);
                }

                //服务器关闭监听
                //channel.closeFuture().sync()实际是如何工作:
                //  channel.closeFuture()不做任何操作，只是简单的返回channel对象中的closeFuture对象，对于每个Channel对象，都会有唯一的一个CloseFuture，用来表示关闭的Future，
                //  所有执行channel.closeFuture().sync()就是执行的CloseFuturn的sync方法，从上面的解释可以知道，这步是会将当前线程阻塞在CloseFuture上*/
                channel.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //关闭事件流
                close();
            }
        }
    }

    /**
     * @descripiton:
     * @author: kinson
     * @date: 2019/8/10 21:17
     * @param
     * @exception：
     * @modifier：
     * @return：void
     */
    public void close() {
        if (null != bossGroup) {
            bossGroup.shutdownGracefully();
        }
        if (null != workerGroup) {
            workerGroup.shutdownGracefully();
        }
    }

    /**
     * @descripiton: 提交任务
     * @author: kinson
     * @date: 2019/8/10 21:24
     * @param task
     * @exception：
     * @modifier：
     * @return：void
     */
    public static void submit(Runnable task) {
        if (null == poolExecutor) {
            synchronized (RpcServer.class) {
                poolExecutor = new ThreadPoolExecutor(16, 16, 600L,
                        TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(65536));
            }
        }
        //执行任务
        poolExecutor.submit(task);
    }

    /**
     * @descripiton: 添加服务接口
     * @author: kinson
     * @date: 2019/8/10 21:33
     * @param interfaceName 服务接口名称
     * @param serviceBean 服务接口对象
     * @exception：
     * @modifier：
     * @return：com.example.springbootrpc.server.RpcServer
     */
    public RpcServer addService(String interfaceName, Object serviceBean) {
        if (!handleMap.containsKey(interfaceName)) {
            logger.info("add service : {}", interfaceName);
            handleMap.put(interfaceName, serviceBean);
        }

        return this;
    }

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        Map<String, Object> serviceBeanMap = ac.getBeansWithAnnotation(RpcService.class);
        if (MapUtils.isNotEmpty(serviceBeanMap)) {
            for (Object serviceBean : serviceBeanMap.values()) {
                RpcService rpcService = serviceBean.getClass().getAnnotation(RpcService.class);
                String interfaceName = rpcService.value().getName();
                logger.info("interfaceName：{}", interfaceName);
                handleMap.put(interfaceName, serviceBean);
            }
        }
    }

}
