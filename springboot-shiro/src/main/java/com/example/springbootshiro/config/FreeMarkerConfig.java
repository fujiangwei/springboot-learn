package com.example.springbootshiro.config;

import com.example.springbootshiro.tags.CustomTagDirective;
import com.jagregory.shiro.freemarker.ShiroTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class FreeMarkerConfig {

    @Autowired
    protected freemarker.template.Configuration configuration;

    @Autowired
    protected CustomTagDirective customTagDirective;

    /**
     * 添加自定义标签
     */
    @PostConstruct
    public void setSharedVariable() {
        System.out.println("=================初始化自定义tag配置=================");
        configuration.setSharedVariable("customTag", customTagDirective);
        //shiro标签
        configuration.setSharedVariable("shiro", new ShiroTags());
    }
}
