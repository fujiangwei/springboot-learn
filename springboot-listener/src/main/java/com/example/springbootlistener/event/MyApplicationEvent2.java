package com.example.springbootlistener.event;

import com.example.springbootlistener.domain.LogPojo2;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author
 * @date
 */
@Getter
public class MyApplicationEvent2 extends ApplicationEvent {

    private LogPojo2 logPojo;

    public MyApplicationEvent2(Object source) {
        super(source);
    }

    public MyApplicationEvent2(Object source, LogPojo2 logPojo) {
        super(source);
        this.logPojo = logPojo;
    }

}