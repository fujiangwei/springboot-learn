package com.example.springbootshiro.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ErrorPagesConfig {

    /**
     * 自定义异常处理路径
     *
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return (container -> {
            ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400");
            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401");
            ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/error/403");
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");
            ErrorPage errorExPage = new ErrorPage(Throwable.class, "/error/500");
            container.addErrorPages(error400Page, error401Page, error403Page, error404Page, error500Page, errorExPage);
        });
    }
}
