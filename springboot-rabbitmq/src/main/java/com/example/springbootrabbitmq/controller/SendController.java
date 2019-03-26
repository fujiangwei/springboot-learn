package com.example.springbootrabbitmq.controller;

import com.example.springbootrabbitmq.service.AckSenderService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/3/22
 * @time: 23:51
 * @modifier:
 * @since:
 */
@RestController
public class SendController {
    /**
     * 默认按类型
     */
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private AckSenderService senderService;

    /**
     * 单条消息发送
     *
     * @return
     */
    @GetMapping(value = "send")
    public String send() {
        String content = "Date:" + System.currentTimeMillis();
        //发送默认交换机对应的的队列kinson
        amqpTemplate.convertAndSend("kinson", content);
        return content;
    }

    /**
     * 发送多条消息到一个队列，该队列有多个消费者
     *
     * @return
     */
    @GetMapping(value = "sendMore")
    public String sendMore() {
        List<String> result = new ArrayList<String>();
        //发送10条数据
        for (int i = 0; i < 10; i++) {
            String content = "第" + (i + 1) + "次发送 Date:" + System.currentTimeMillis();
            //发送默认交换机对应的的队列kinson,此时有两个消费者MyReceiver1和MyReceiver2,每条消息只会被消费一次
            amqpTemplate.convertAndSend("kinson", content);
            result.add(content);
        }
        return String.join("<br/>", result);
    }

    /**
     * 发送多条消息到多个队列
     *
     * @return
     */
    @GetMapping(value = "sendMoreQueue")
    public String sendMoreQueue() {
        List<String> result = new ArrayList<String>();
        //发送10条数据
        for (int i = 0; i < 10; i++) {
            String content = "第" + (i + 1) + "次发送 Date:" + System.currentTimeMillis();
            //发送默认交换机对应的的队列kinson
            amqpTemplate.convertAndSend("kinson", content);
            //发送默认交换机对应的的队列kinson2
            amqpTemplate.convertAndSend("kinson2", content);
            result.add(content);
        }
        return String.join("<br/>", result);
    }

    /**
     * Topic模式的exchange消息发送，此时topic.msg符合队列topic.msg和topic.msgs绑定的routingKey，
     * 故发送的消息会传送到队列topic.msg和topic.msgs
     *
     * @return
     */
    @GetMapping(value = "topicSend")
    public String topicSend() {
        String content = "topicSend :" + System.currentTimeMillis();
        //发送topicExchange交换机对应的的队列topic.msg
        amqpTemplate.convertAndSend("topicExchange", "topic.msg", content);
        return content;
    }

    /**
     * Topic模式的exchange消息发送，此时topic.msgs符合只符合topic.msgs绑定的routingKey，
     * 故发送的消息只传送到队列topic.msgs
     *
     * @return
     */
    @GetMapping(value = "topicSend2")
    public String topicSend2() {
        String content = "topicSend2 :" + System.currentTimeMillis();
        //发送topicExchange交换机对应的的队列topic.msgs
        amqpTemplate.convertAndSend("topicExchange", "topic.msgs", content);
        return content;
    }

    /**
     * @return
     */
    @GetMapping(value = "ackSend")
    public String ackSend() {
        senderService.send();

        return "ok";
    }

    /**
     * @return
     */
    @GetMapping(value = "sendObj")
    public String sendObj() {
        senderService.sendObj();

        return "ok";
    }

}
