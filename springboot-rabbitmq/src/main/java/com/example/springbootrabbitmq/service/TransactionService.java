package com.example.springbootrabbitmq.service;

import com.example.springbootrabbitmq.utils.RabbitMqConnFactoryUtil;
import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

/**
 * RabbitMQ事物示例
 * <p>
 * 事务的实现主要是对信道（Channel）的设置，主要的方法有三个：
 * channel.txSelect()声明启动事务模式；
 * channel.txComment()提交事务；
 * channel.txRollback()回滚事务；
 * <p/>
 * <p>
 * <p>
 * 消费者模式使用事务
 * 假设消费者模式中使用了事务，并且在消息确认之后进行了事务回滚，结果分为两种情况：
 * autoAck=false手动应对的时候是支持事务的，也就是说即使你已经手动确认了消息已经收到了，但在确认消息会等事务的返回解决之后，在做决定是确认消息还是重新放回队列，如果你手动确认现在之后，又回滚了事务，那么已事务回滚为主，此条消息会重新放回队列；
 * autoAck=true如果自定确认为true的情况是不支持事务的，也就是说你即使在收到消息之后在回滚事务也是于事无补的，队列已经把消息移除了；
 * </p>
 */
@Service
public class TransactionService {

    /**
     * 队列名称
     */
    private static final String TX_QUEUE = "tx_queue";

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    /**
     * 发送消息
     *
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws URISyntaxException
     * @throws IOException
     * @throws TimeoutException
     */
    public void publish()
            throws KeyManagementException, NoSuchAlgorithmException, URISyntaxException, IOException, TimeoutException {
        // 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setVirtualHost("/");
        factory.setHost(host);
        factory.setPort(port);
        Connection conn = factory.newConnection();
        // 创建信道
        Channel channel = conn.createChannel();
        // 声明队列
        channel.queueDeclare(TX_QUEUE, true, false, false, null);

        try {

            long startTime = System.currentTimeMillis();

            for (int i = 0; i < 10; i++) {
                // 声明事务
                channel.txSelect();
                String message = String.format("时间 => %s", System.currentTimeMillis());
                // 发送消息
                channel.basicPublish("", TX_QUEUE, MessageProperties.PERSISTENT_TEXT_PLAIN,
                        message.getBytes("UTF-8"));
                // 提交事务
                channel.txCommit();
            }

            long endTime = System.currentTimeMillis();

            System.out.println("事务模式，发送10条数据，执行花费时间：" + (endTime - startTime) + "s");

        } catch (Exception e) {
            channel.txRollback();
        } finally {
            channel.close();
            conn.close();
        }
    }

    /**
     * 消费消息，单条消息消费
     *
     * @throws IOException
     * @throws TimeoutException
     * @throws InterruptedException
     */
    public void consume() throws IOException, TimeoutException, InterruptedException {

        Connection conn = RabbitMqConnFactoryUtil.getRabbitConn();
        Channel channel = conn.createChannel();
        channel.queueDeclare(TX_QUEUE, true, false, false, null);
        // 声明事务
        channel.txSelect();
        try {
            //单条消息获取进行消费
            GetResponse resp = channel.basicGet(TX_QUEUE, false);
            String message = new String(resp.getBody(), "UTF-8");
            System.out.println("收到消息：" + message);
            //消息拒绝
            // channel.basicReject(resp.getEnvelope().getDeliveryTag(), true);
            // 消息确认
            channel.basicAck(resp.getEnvelope().getDeliveryTag(), false);
            // 提交事务
            channel.txCommit();
        } catch (Exception e) {
            // 回滚事务
            channel.txRollback();
        } finally {
            //关闭通道、连接
            channel.close();
            conn.close();
        }
    }

}