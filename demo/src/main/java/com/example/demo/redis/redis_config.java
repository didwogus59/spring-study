package com.example.demo.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import org.springframework.beans.factory.annotation.Value;

@Configuration
@PropertySource("classpath:redis.properties")
public class redis_config {

    @Value("${host}")
    private String host;

    @Value("${port}")
    private int port;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        System.out.printf("%s", host);
        return new LettuceConnectionFactory(host, port);
    }
}