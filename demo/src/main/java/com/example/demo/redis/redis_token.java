package com.example.demo.redis;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;
import org.springframework.security.core.Authentication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RedisHash(value = "keys")
public class redis_token {

    @Id
    private String authId;

    @Indexed
    private String sessionId;

    @TimeToLive
    private long ttl;

    private Authentication auth;

    public redis_token (String token, long ttl) {
        this.sessionId = token;
        this.ttl = ttl;
    }

}
