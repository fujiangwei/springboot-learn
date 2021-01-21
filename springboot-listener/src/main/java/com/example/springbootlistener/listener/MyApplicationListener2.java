package com.example.springbootlistener.listener;

import com.example.springbootlistener.event.MyApplicationEvent;
import com.example.springbootlistener.event.MyApplicationEvent2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author
 * @date
 */
@Slf4j
@Component
public class MyApplicationListener2 {

    /**
     * @EventListener注解
     *      主要通过EventListenerMethodProcessor扫描出所有带有@EventListener注解的方法，然后动态构造事件监听器，并将监听器托管到Spring应用上文中。
     * @param myApplicationEvent
     */
    @Async
    @EventListener
    public void onApplicationEvent(MyApplicationEvent2 myApplicationEvent) {
        log.info("MyApplicationListener2:" + myApplicationEvent.getLogPojo().getLogName());
    }
}