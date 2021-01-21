package com.example.springbootlistener.listener;

import com.example.springbootlistener.event.MyApplicationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author
 * @date
 */
@Slf4j
//@Component
public class MyApplicationListener3 implements ApplicationListener<MyApplicationEvent> {

    /**
     * @param myApplicationEvent
     */
    @Override
    public void onApplicationEvent(MyApplicationEvent myApplicationEvent) {
        log.info("MyApplicationListener3 :" + myApplicationEvent.getLogPojo().getLogName());
    }
}