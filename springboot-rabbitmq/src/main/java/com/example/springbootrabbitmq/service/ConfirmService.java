package com.example.springbootrabbitmq.service;

import com.example.springbootrabbitmq.utils.RabbitMqConnFactoryUtil;
import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * RabbitMQConfirm发送方确认示例
 * Confirm发送方确认模式
 * Confirm发送方确认模式使用和事务类似，也是通过设置Channel进行发送方确认的。
 * <p>
 * Confirm的三种实现方式：
 * <p>
 * 方式一：channel.waitForConfirms()普通发送方确认模式；
 * <p>
 * 方式二：channel.waitForConfirmsOrDie()批量确认模式；
 * <p>
 * 方式三：channel.addConfirmListener()异步监听发送方确认模式；
 */
@Service
public class ConfirmService {

    /**
     * 队列名称
     */
    private static final String CONFIRM_QUEUE = "confirm_queue";

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    public void publish() throws IOException, TimeoutException, InterruptedException {
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
        channel.queueDeclare(CONFIRM_QUEUE, false, false, false, null);

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {
            // 开启发送方确认模式
            channel.confirmSelect();
            String message = String.format("时间 => %s", System.currentTimeMillis());
            channel.basicPublish("", CONFIRM_QUEUE, null, message.getBytes("UTF-8"));
        }

        //添加确认监听器
        channel.addConfirmListener(new ConfirmListener() {

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("未确认消息，标识：" + deliveryTag);
            }

            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println(String.format("已确认消息，标识：%d，多个消息：%b", deliveryTag, multiple));
            }
        });

        long endTime = System.currentTimeMillis();

        System.out.println("执行花费时间：" + (endTime - startTime) + "s");

        // //方式一：普通Confirm模式
        // if (channel.waitForConfirms()) {
        // System.out.println("消息发送成功");
        // }

        // 方式二：批量确认模式
        // channel.waitForConfirmsOrDie(); // 直到所有信息都发布，只要有一个未确认就会IOException
        // System.out.println("全部执行完成");

        // 方式三：异步确认模式
        // channel.addConfirmListener(new ConfirmListener() {
        //
        // @Override
        // public void handleNack(long deliveryTag, boolean multiple) throws
        // IOException {
        // System.out.println("未确认消息，标识：" + deliveryTag);
        // }
        //
        // @Override
        // public void handleAck(long deliveryTag, boolean multiple) throws
        // IOException {
        // System.out.println(String.format("已确认消息，标识：%d，多个消息：%b", deliveryTag,
        // multiple));
        // }
        // });
        //
        // System.out.println("程序执行完成：" + System.currentTimeMillis());

    }

    /**
     * 消费消息
     *
     * @throws IOException
     * @throws TimeoutException
     * @throws InterruptedException
     */
    public void consume() throws IOException, TimeoutException, InterruptedException {

        Connection conn = RabbitMqConnFactoryUtil.getRabbitConn();
        Channel channel = conn.createChannel();
        // 声明队列【参数说明：参数一：队列名称，参数二：是否持久化；参数三：是否独占模式；参数四：消费者断开连接时是否删除队列；参数五：消息其他参数】
        channel.queueDeclare(CONFIRM_QUEUE, false, false, false, null);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                // 队列名称
                // String routingKey = envelope.getRoutingKey();
                // 内容类型
                // String messageType = properties.getmessageType();
                // 消息正文
                String message = new String(body, "utf-8");
                System.out.println(CONFIRM_QUEUE + "消费消息 => " + message);
                try {
                    Thread.sleep(1 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 手动确认消息【参数说明：参数一：该消息的index；参数二：是否批量应答，true批量确认小于index的消息】
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };
        // 消费消息
        channel.basicConsume(CONFIRM_QUEUE, false, consumer);
    }
}