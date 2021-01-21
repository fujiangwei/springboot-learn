package com.example.springbootlistener.listener;

import com.example.springbootlistener.event.MyApplicationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author
 * @date
 */
@Slf4j
@Component
public class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {

    @Async
    @Override
    public void onApplicationEvent(MyApplicationEvent myApplicationEvent) {
        log.info("MyApplicationListener:" + myApplicationEvent.getLogPojo().getLogName());
    }
}