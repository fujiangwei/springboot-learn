package com.example.springbootfreemarker.config;

import com.example.springbootfreemarker.tags.directive.MyTagDirective;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * descripiton:
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/2/16
 * @time: 21:07
 * @modifier:
 * @since:
 */
@Configuration
public class FreeMarkerConfig {

    @Autowired
    private freemarker.template.Configuration configuration;

    @Autowired
    private MyTagDirective myTagDirective;

    @PostConstruct //启动时加载
    public void setSharedVariable() throws TemplateModelException {
        System.out.println("=================初始化自定义tag配置=================");
        configuration.setSharedVariable("myTag", myTagDirective);
    }

}
