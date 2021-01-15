package com.springboot.learn.configuration;

import com.springboot.learn.config.KingsonProperties;
import com.springboot.learn.service.KingsonService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author
 * @Description: 文件描述
 * @date
 **/
@Configuration
@ConditionalOnClass(KingsonService.class)
@ConditionalOnProperty(prefix = "kingson", value = "enable", matchIfMissing = true)
@EnableConfigurationProperties(KingsonProperties.class)
public class KingsonAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public KingsonService kingsonGet() {
        return new KingsonService();
    }
}