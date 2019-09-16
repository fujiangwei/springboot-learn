package com.example.springbooti18n.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * descripiton:
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/2/18
 * @time: 15:48
 * @modifier:
 * @since:
 */
@Component
public class MessagesUtil {

    @Autowired
    private MessageSource messageSource;

    /**
     * @param key：对应文本配置的key.
     * @return 对应地区的语言消息字符串
     */
    public String getMessage(String key) {
        return this.getMessage(key, new Object[]{});
    }

    public String getMessage(String key, String defaultMessage) {
        return this.getMessage(key, null, defaultMessage);
    }

    public String getMessage(String key, String defaultMessage, Locale locale) {
        return this.getMessage(key, null, defaultMessage, locale);
    }

    public String getMessage(String key, Locale locale) {
        return this.getMessage(key, null, "", locale);
    }

    public String getMessage(String key, Object[] args) {
        return this.getMessage(key, args, "");
    }

    public String getMessage(String key, Object[] args, Locale locale) {
        return this.getMessage(key, args, "", locale);
    }

    public String getMessage(String key, Object[] args, String defaultMessage) {
        Locale locale = LocaleContextHolder.getLocale();
        return this.getMessage(key, args, defaultMessage, locale);
    }

    public String getMessage(String key, Object[] args, String defaultMessage, Locale locale) {
        return messageSource.getMessage(key, args, defaultMessage, locale);
    }
}
