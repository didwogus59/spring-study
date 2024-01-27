package com.example.demo.redis;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import lombok.Getter;

@Getter
@RedisHash(value = "keys")
public class redis_token {

    @Id
    private String authId;

    @Indexed
    private String token;

    private String role;

    @TimeToLive
    private long ttl;

    public redis_token (String token, long ttl) {
        this.token = token;
        this.ttl = ttl;
    }
}
