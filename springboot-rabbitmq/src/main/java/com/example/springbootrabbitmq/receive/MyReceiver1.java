package com.example.springbootrabbitmq.receive;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
//监听队列kinson
@RabbitListener(queues = {"kinson"})
public class MyReceiver1 {

    @RabbitHandler
    public void receiver(String msg) {
        System.out.println("MyReceiver1 :" + msg);
    }
}