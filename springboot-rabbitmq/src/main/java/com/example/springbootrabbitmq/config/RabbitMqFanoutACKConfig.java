package com.example.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * descripiton: 设置消息确认队列
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/3/22
 * @time: 23:49
 * @modifier:
 * @since:
 */
@Configuration
public class RabbitMqFanoutACKConfig {

    @Bean
    public Queue ackQueue() {
        return new Queue("ackQueue");
    }

    @Bean
    public Queue ackQueue2() {
        return new Queue("ackQueue2");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingAckQueue2Exchange(Queue ackQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(ackQueue).to(fanoutExchange);
    }

    @Bean
    Binding bindingAckQueue22Exchange(Queue ackQueue2, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(ackQueue2).to(fanoutExchange);
    }
}
