package com.example.springbootrpc.client;

import com.example.springbootrpc.client.proxy.IAsyncObjectProxy;
import com.example.springbootrpc.client.proxy.ObjectProxy;
import com.example.springbootrpc.registry.ServiceDiscovery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/8/13
 * @time: 22:49
 * @modifier:
 * @since:
 */
public class RpcClient {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(RpcClient.class);


    private String serverIp;

    private int port;

    private ServiceDiscovery serviceDiscovery;

    private static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(16, 16,
            600L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(65536));

    public RpcClient(String serverIp, int port) {
        this.serverIp = serverIp;
        this.port = port;
    }

    public RpcClient(ServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    public static <T>T create(Class<T> interfaceClass) {
        return (T)Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                new Class[]{interfaceClass},
                new ObjectProxy<T>(interfaceClass));
    }

    public static <T>IAsyncObjectProxy createAsyn(Class<T> interfaceClass) {
        return new ObjectProxy<T>(interfaceClass);
    }

    public static void submit(Runnable task) {
        poolExecutor.submit(task);
    }

    public void stop() {
        poolExecutor.shutdown();
        serviceDiscovery.stop();
        ConnectManage.getInstance().stop();
    }
}
