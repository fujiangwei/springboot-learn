package com.example.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * descripiton:Topic交换机配置类
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/3/25
 * @time: 12:48
 * @modifier:
 * @since:
 */
@Configuration
public class RabbitMqTopicConfig {

    /**
     * 只接一个topic
     */
    @Bean
    public Queue queueMsg() {
        return new Queue("topic.msg");
    }

    /**
     * 接收多个topic队列
     */
    @Bean
    public Queue queueMsgs() {
        return new Queue("topic.msgs");
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }

    /**
     * 绑定topic.msg到交换机topicExchange，rotingKey为topic.#，可以匹配为topic.msg的路由Key对应的消息
     * @param queueMsg
     * @param topicExchange
     * @return
     */
    @Bean
    public Binding bindingQueue2Exchange(Queue queueMsg, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueMsg).to(topicExchange).with("topic.msg");
    }

    /**
     * 绑定topic.msgs到交换机topicExchange，rotingKey为topic.#，可以匹配以topic.开头的所有的路由Key对应的消息
     * @param queueMsgs
     * @param topicExchange
     * @return
     */
    @Bean
    public Binding bindingQueue2Exchange2(Queue queueMsgs, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueMsgs).to(topicExchange).with("topic.#");
    }

}
