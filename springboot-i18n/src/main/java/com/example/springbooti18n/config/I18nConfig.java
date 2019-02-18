package com.example.springbooti18n.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/2/18
 * @time: 16:46
 * @modifier:
 * @since:
 */
@Configuration
public class I18nConfig implements WebMvcConfigurer {

    /**
     * Session方式
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        //设置默认区域
        slr.setDefaultLocale(Locale.ENGLISH);

        return slr;
    }

    /**
     * Cookie方式
     */
//    @Bean
//    public LocaleResolver localeResolver2() {
//        CookieLocaleResolver clr = new CookieLocaleResolver();
//        clr.setCookieName("localeCookie");
//        //设置默认区域
//        clr.setDefaultLocale(Locale.ENGLISH);
//        //设置cookie有效期.
//        clr.setCookieMaxAge(3600);
//        return clr;
//    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        //对请求页面路径中的参数lang进行拦截
        lci.setParamName("lang");

        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
