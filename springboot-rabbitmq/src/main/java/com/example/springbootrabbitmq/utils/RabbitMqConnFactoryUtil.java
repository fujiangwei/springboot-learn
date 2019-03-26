package com.example.springbootrabbitmq.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

/**
 * @author kinson
 */
public class RabbitMqConnFactoryUtil {

    /**
     * 获取rabbit连接
     */
    public static Connection getRabbitConn() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        factory.setHost("192.168.242.131");
        factory.setPort(5672);
        Connection conn = factory.newConnection();

        //高级连接使用线程池
        // ExecutorService es = Executors.newFixedThreadPool(20);
        // Connection conn = factory.newConnection(es);

        return conn;
    }

    /**
     * 获取rabbit连接方式二
     */
    public static Connection getRabbitConn2()
            throws KeyManagementException, NoSuchAlgorithmException, URISyntaxException, IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        // 连接格式：amqp://userName:password@hostName:portNumber/virtualHost
        String uri = String.format("amqp://%s:%s@%s:%d%s", "guest", "guest",
                "192.168.242.131", 5672, "/");
        factory.setUri(uri);
        factory.setVirtualHost("/");
        Connection conn = factory.newConnection();
        return conn;
    }
}