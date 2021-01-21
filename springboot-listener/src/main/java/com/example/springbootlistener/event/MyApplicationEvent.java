package com.example.springbootlistener.event;

import com.example.springbootlistener.domain.LogPojo;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author
 * @date
 */
@Getter
public class MyApplicationEvent extends ApplicationEvent {

    private LogPojo logPojo;

    public MyApplicationEvent(Object source) {
        super(source);
    }

    public MyApplicationEvent(Object source, LogPojo logPojo) {
        super(source);
        this.logPojo = logPojo;
    }

}