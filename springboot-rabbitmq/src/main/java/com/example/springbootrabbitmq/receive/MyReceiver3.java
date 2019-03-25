package com.example.springbootrabbitmq.receive;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
//监听队列kinson2
@RabbitListener(queues = {"kinson2"})
public class MyReceiver3 {

    @RabbitHandler
    public void receiver(String msg) {
        System.out.println("MyReceiver3 :" + msg);
    }
}