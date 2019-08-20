package com.example.springbootrpc.registry;

import com.example.springbootrpc.consts.Constant;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/8/10
 * @time: 18:02
 * @modifier:
 * @since:
 */
public class ServiceRegistry {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(ServiceRegistry.class);

    /**
     * 服务注册地址
     */
    private String registryAddress;

    /**
     * 计数器
     */
    private CountDownLatch latch = new CountDownLatch(1);

    public ServiceRegistry(String registryAddress) {
        this.registryAddress = registryAddress;
    }

    /**
     * @descripiton: 注册
     * @author: kinson
     * @date: 2019/8/10 22:03
     * @param data 待注册数据
     * @exception：
     * @modifier：
     * @return：void
     */
    public void registry(String data) {

        if (null != data) {
            ZooKeeper zk = connectServer();
            if (null != zk) {
                addRootNode(zk);
                createNode(zk, data);
            }
        }
    }

    /**
     * @descripiton: 连接zk服务
     * @author: kinson
     * @date: 2019/8/10 22:19
     * @param
     * @exception：
     * @modifier：
     * @return：org.apache.zookeeper.ZooKeeper
     */
    private ZooKeeper connectServer() {
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper(registryAddress, Constant.ZK_SESSION_TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if(event.getState() == Event.KeeperState.SyncConnected) {
                        latch.countDown();
                    }
                }
            });
        } catch (Exception e) {
            logger.error("连接服务器异常：{}", e);
        } finally {
            try {
                // 后续的线程服务需等连接上服务之后才能继续执行
                latch.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return zk;
    }

    /**
     * @descripiton: 添加根节点
     * @author: kinson
     * @date: 2019/8/10 22:18
     * @param zk
     * @exception：
     * @modifier：
     * @return：void
     */
    private void addRootNode(ZooKeeper zk) {
        try {
            Stat stat = zk.exists(Constant.ZK_REGISTRY_PATH, Boolean.FALSE);
            if (null == stat) {
                zk.create(Constant.ZK_REGISTRY_PATH, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            }
        } catch (Exception e) {
            logger.error("添加根节点异常：{}", e);
        }
    }

    /**
     * @descripiton: 创建节点
     * @author: kinson
     * @date: 2019/8/10 22:19
     * @param zk
     * @param data
     * @exception：
     * @modifier：
     * @return：void
     */
    private void createNode(ZooKeeper zk, String data) {
        try {
            byte[] bytes = data.getBytes();
            String path = zk.create(Constant.ZK_DATA_PATH, bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            logger.debug("create zk node: ({} => {})", path, data);
        } catch (Exception e) {
            logger.error("创建节点异常：{}", e);
        }
    }
}
