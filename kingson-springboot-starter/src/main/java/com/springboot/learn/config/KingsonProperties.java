package com.springboot.learn.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author
 * @Description: 文件描述
 * @date
 **/
@Configuration
@ConfigurationProperties(prefix = "kingson")
@PropertySource(value = "classpath:/kingson.properties")
public class KingsonProperties {

    private String username;

    private String password;

    private boolean enabled;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}