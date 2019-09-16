package com.example.springbootrabbitmq.receive;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * descripiton:
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/3/26
 * @time: 12:17
 * @modifier:
 * @since:
 */
@Component
@RabbitListener(queues = {"ackQueue"})
public class MyAckReceiver {

    @RabbitHandler
    public void process(String sendMsg, Channel channel, Message message) {

        System.out.println("AckReceiver  : 收到发送消息 " + sendMsg + ",收到消息时间"
                + LocalDateTime.now(ZoneId.systemDefault()));

        try {
            //告诉服务器收到这条消息已经被当前消费者消费了，可以在队列安全删除，这样后面就不会再重发了，
            //否则消息服务器以为这条消息没处理掉，后续还会再发
            //第二个参数是消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            System.out.println("process success");
        } catch (Exception e) {
            System.out.println("process fail");
            try {
                //ack返回false，并重新回到队列
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

    }
}
