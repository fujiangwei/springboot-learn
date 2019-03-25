package com.example.springbootrabbitmq.receive;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
//监听队列topic.msgs
@RabbitListener(queues = {"topic.msgs"})
public class MyTopicReceiver2 {

    @RabbitHandler
    public void receiver(String msg) {
        System.out.println("MyTopicReceiver2 :" + msg);
    }
}