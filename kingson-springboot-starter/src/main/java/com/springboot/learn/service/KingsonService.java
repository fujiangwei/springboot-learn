package com.springboot.learn.service;

import com.springboot.learn.config.KingsonProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @Description: 文件描述
 * @date
 **/
@Service
public class KingsonService {

    @Autowired
    private KingsonProperties kingsonProperties;

    public String usernameGet() {
        return kingsonProperties.getUsername();
    }

    public String passwordGet() {
        return kingsonProperties.getPassword();
    }

    public boolean enabledGet() {
        return kingsonProperties.isEnabled();
    }

    public String userGet() {
        if (kingsonProperties.isEnabled()) {
            return kingsonProperties.getUsername() + " - " + kingsonProperties.getPassword();
        }

        return kingsonProperties.getUsername();
    }
}