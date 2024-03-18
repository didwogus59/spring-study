package com.example.demo.WebFlux.stream;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class stream_config {
    
    @Bean
    public FilterRegistrationBean<stream_filter> stream_filter_test() {
        FilterRegistrationBean<stream_filter> bean = new FilterRegistrationBean<>(new stream_filter());
        bean.addUrlPatterns("/stream");
        bean.setOrder(0);
        return bean;
    }
}
