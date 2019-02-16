package com.example.springbootshiro.config;

import com.example.springbootshiro.interceptor.RememberAuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private RememberAuthenticationInterceptor rememberAuthenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rememberAuthenticationInterceptor)
                .excludePathPatterns("/login", "/error/**", "/assets/**", "/static/**")
                .addPathPatterns("/**");

    }
}
