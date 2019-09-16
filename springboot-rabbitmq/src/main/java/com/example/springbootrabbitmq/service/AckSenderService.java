package com.example.springbootrabbitmq.service;

import com.alibaba.fastjson.JSON;
import com.example.springbootrabbitmq.domain.MessageObj;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * descripiton: ACK发送服务
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/3/26
 * @time: 12:02
 * @modifier:
 * @since:
 */
@Service
public class AckSenderService implements RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println("AckSenderService returnedMessage " + message.toString() + " === " + i + " === " + s1 + " === " + s2);
    }

    /**
     * 消息发送
     */
    public void send() {
        final String content = "现在时间是" + LocalDateTime.now(ZoneId.systemDefault());

        //设置返回回调
        rabbitTemplate.setReturnCallback(this);
        //设置确认回调
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                System.out.println("消息发送成功！");
            } else {
                System.out.println("消息发送失败，" + cause + correlationData.toString());
            }
        });
        rabbitTemplate.convertAndSend("ackQueue", content);
    }

    /**
     * 发送对象，序列化处理
     */
    public void sendObj() {
        MessageObj messageObj = new MessageObj();
        messageObj.setId(1111);
        messageObj.setName("kinson");
        messageObj.setAck(Boolean.TRUE);

        rabbitTemplate.convertAndSend("ackQueue2", JSON.toJSONString(messageObj));
    }
}
