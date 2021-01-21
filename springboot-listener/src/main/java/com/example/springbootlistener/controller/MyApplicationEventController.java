package com.example.springbootlistener.controller;

import com.example.springbootlistener.domain.LogPojo;
import com.example.springbootlistener.domain.LogPojo2;
import com.example.springbootlistener.event.MyApplicationEvent;
import com.example.springbootlistener.event.MyApplicationEvent2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @date
 */
@RestController
public class MyApplicationEventController {

    @Autowired
    private ApplicationContext publisher;

    @RequestMapping("/publisher")
    public String publisher(String logName) {
        LogPojo logPojo = new LogPojo(StringUtils.isEmpty(logName) ? "Kingson" : logName);
        MyApplicationEvent event = new MyApplicationEvent(this, logPojo);
        publisher.publishEvent(event);

        return logPojo.getLogName();
    }

    @RequestMapping("/publisher2")
    public String publisher2(String logName) {
        LogPojo2 logPojo = new LogPojo2(StringUtils.isEmpty(logName) ? "Kingson2" : logName);
        MyApplicationEvent2 event = new MyApplicationEvent2(this, logPojo);
        publisher.publishEvent(event);

        return logPojo.getLogName();
    }
}