package com.example.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.jwt.jwt;

@Configuration
public class FilterConfig {
    
    @Bean
    public FilterRegistrationBean<myfilter1> filter1() {
        FilterRegistrationBean<myfilter1> bean = new FilterRegistrationBean<>(new myfilter1());
        bean.addUrlPatterns("/*");
        bean.setOrder(0);
        return bean;
    }
}
