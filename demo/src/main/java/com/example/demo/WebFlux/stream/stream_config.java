package com.example.demo.WebFlux.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.Filter;

@Configuration
public class stream_config {
    
    @Autowired
    simple_event event;

    @Bean
    public FilterRegistrationBean<Filter> stream_filter_test() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>(new stream_filter(event));
        bean.addUrlPatterns("/stream");
        bean.setOrder(0);
        return bean;
    }

    @Bean
    public FilterRegistrationBean<Filter> event_filter_test() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>(new event_filter(event));
        bean.addUrlPatterns("/stream/event");
        bean.setOrder(0);
        return bean;
    }
}
