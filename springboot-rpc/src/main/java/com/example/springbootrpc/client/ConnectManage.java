package com.example.springbootrpc.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/8/13
 * @time: 23:31
 * @modifier:
 * @since:
 */
public class ConnectManage {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectManage.class);

    private volatile static ConnectManage connectManage;

    private EventLoopGroup eventLoopGroup = new NioEventLoopGroup(4);

    private static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(16, 16,
            600L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(65536));


    private CopyOnWriteArrayList<RpcClientHandler> connectedHandlers = new CopyOnWriteArrayList();

    private Map<InetSocketAddress, RpcClientHandler> connectedServerNodes = new ConcurrentHashMap<>();

    private ReentrantLock lock = new ReentrantLock();
    private Condition connected = lock.newCondition();
    private long currentTimeoutMillis = 6000;
    private AtomicInteger roundRobin = new AtomicInteger(0);
    private volatile boolean isRunning = Boolean.TRUE;

    private ConnectManage() {

    }

    public static ConnectManage getInstance() {
        if ( null == connectManage) {
            synchronized (ConnectManage.class) {
                if (null == connectManage) {
                    connectManage = new ConnectManage();
                }
            }
        }

        return connectManage;
    }

    public void updateConnectedServer(List<String> allServerAddress) {
        if (CollectionUtils.isNotEmpty(allServerAddress)) {
            //update local serverNodes cache
            Set<InetSocketAddress> newAllServerNodeSet = new HashSet<InetSocketAddress>();

            for (int i = 0; i < allServerAddress.size(); i ++) {
                String[] array = allServerAddress.get(i).split(":");
                // Should check IP and port
                if (array.length == 2) {
                    String host = array[0];
                    int port = Integer.parseInt(array[1]);
                    final InetSocketAddress remotePeer = new InetSocketAddress(host, port);
                    newAllServerNodeSet.add(remotePeer);
                }
            }

            // Add new server node
            for (final InetSocketAddress inetSocketAddress : newAllServerNodeSet) {
                if (!connectedServerNodes.keySet().contains(inetSocketAddress)) {
                    connectServerNode(inetSocketAddress);
                }
            }

            // Close and remove invalid server nodes
            for (int i = 0; i < connectedHandlers.size(); i ++) {
                RpcClientHandler connectedServerHandler  = connectedHandlers.get(i);
                SocketAddress remotePeer = connectedServerHandler.getRemotePeer();
                if (!newAllServerNodeSet.contains(remotePeer)) {
                    LOGGER.info("Remove invalid server node {}", remotePeer);
                    RpcClientHandler handler = connectedServerNodes.get(remotePeer);
                    if (null != handler) {
                        handler.close();
                    }
                    connectedServerNodes.remove(remotePeer);
                    connectedHandlers.remove(remotePeer);
                }
            }
        } else { // No available server node ( All server nodes are down )
            LOGGER.error("No available server node. All server nodes are down !!!");
            for (final RpcClientHandler connectedServerHandler : connectedHandlers) {
                SocketAddress remotePeer = connectedServerHandler.getRemotePeer();
                RpcClientHandler handler = connectedServerNodes.get(remotePeer);
                if (null != handler) {
                    handler.close();
                }
                connectedServerNodes.remove(remotePeer);
            }
            connectedHandlers.clear();
        }
    }

    public void reconnect(final RpcClientHandler handler, final SocketAddress remotePeer) {
        if (null != handler) {
            // 先移除后连接
            connectedHandlers.remove(handler);
            connectedServerNodes.remove(remotePeer);
        }
        connectServerNode((InetSocketAddress) remotePeer);
    }

    private void connectServerNode(final InetSocketAddress remotePeer) {
        poolExecutor.submit(() -> {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new RpcClientHandler());

            ChannelFuture channelFuture = bootstrap.connect(remotePeer);
            channelFuture.addListener((channel) -> {
                if (channel.isSuccess()) {
                    LOGGER.debug("Successfully connect to remote server. remote peer = " + remotePeer);
                    RpcClientHandler handler = channelFuture.channel().pipeline().get(RpcClientHandler.class);
                    addHandler(handler);
                }
            });
        });
    }

    private void addHandler(RpcClientHandler handler) {
        connectedHandlers.add(handler);
        InetSocketAddress remoteAddress = (InetSocketAddress)handler.getChannel().remoteAddress();
        connectedServerNodes.put(remoteAddress, handler);
        signalAvailableHandler();
    }

    public RpcClientHandler chooseHandler() {
        RpcClientHandler handler = new RpcClientHandler();


        int size = connectedHandlers.size();
        while (isRunning && size <= 0) {
            try {
                boolean available = waitingForHandler();
                if (available) {
                    size = connectedHandlers.size();
                }
            } catch (Exception e) {
                LOGGER.error("Waiting for available node is interrupted! ", e);
                throw new RuntimeException("Can't connect any servers!", e);
            }

            int index = (roundRobin.getAndAdd(1) + size) % size;

            return connectedHandlers.get(index);
        }

        return handler;
    }

    private boolean waitingForHandler() throws InterruptedException{
        lock.lock();

        try {
            // 当前线程调用condition.await()方法后，会使得当前线程释放lock然后加入到等待队列中，
            // 直至被signal/signalAll后会使得当前线程从等待队列中移至到同步队列中去，
            // 直到获得了lock后才会从await方法返回，或者在等待时被中断会做中断处理。
            return connected.await(this.currentTimeoutMillis, TimeUnit.MILLISECONDS);
        } finally {
            lock.unlock();
        }
    }

    public void stop() {
        isRunning = false;
        for (int i = 0; i < connectedHandlers.size(); i ++) {
            RpcClientHandler connectedServerHandler = connectedHandlers.get(i);
            connectedServerHandler.close();
        }
        signalAvailableHandler();
        poolExecutor.shutdown();
        eventLoopGroup.shutdownGracefully();
    }

    private void signalAvailableHandler() {
        lock.lock();
        try {
            connected.signalAll();
        } finally {
            lock.unlock();
        }
    }

}
