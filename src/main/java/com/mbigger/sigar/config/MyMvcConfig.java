package com.mbigger.sigar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MyMvcConfig {
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            //配置跨域
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("http://localhost:8080")     //允许的路径
                        .allowedMethods("*")     //允许的方法
                        .allowedOrigins("*")       //允许的网站
                        .allowedHeaders("*")     //允许的请求头
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };

    }
}
