package com.example.demo.redis;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.FlushMode;
import org.springframework.session.SaveMode;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;

import java.time.Duration;

@Configuration
public class redis_session_config extends RedisHttpSessionConfiguration {
    
    
    @Override
    protected Duration getMaxInactiveInterval() {
        Duration duration = Duration.ofHours(12);
        return duration;
    }

    @Override
    protected String getRedisNamespace() {
        return "testSpace";
    }

    // you can remove it if you accept the defaults below
    @Override
    protected FlushMode getFlushMode() {
        return FlushMode.ON_SAVE;
    }

    @Override
    protected SaveMode getSaveMode() {
        return SaveMode.ON_SET_ATTRIBUTE;
    }
}
