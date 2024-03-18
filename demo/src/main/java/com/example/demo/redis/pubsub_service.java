package com.example.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import io.lettuce.core.RedisClient;
import io.lettuce.core.pubsub.RedisPubSubListener;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;

@Service
public class pubsub_service {
    
    RedisClient client = null;
    StatefulRedisPubSubConnection<String, String> connect = null;

    @Qualifier("forPub") 
    @Autowired
    RedisTemplate<String, String> template;

    @Autowired
    public pubsub_service(RedisClient client) {  
        this.client = client;
        connect = client.connectPubSub();

        connect.addListener(new RedisPubSubListener<String,String>() {

            @Override
            public void message(String channel, String message) {
                System.out.println("channel: " + channel + " Message: " + message);
            }

            @Override
            public void message(String pattern, String channel, String message) {
                System.out.println("pattern: " + pattern + " channel: " + channel + " Message: " + message);
            }

            @Override
            public void subscribed(String channel, long count) {
                System.out.println("channel: " + channel + " count: " + count);
            }

            @Override
            public void psubscribed(String pattern, long count) {
                System.out.println("patter: " + pattern + " count: " + count);
            }

            @Override
            public void unsubscribed(String channel, long count) {
                System.out.println("channel: " + channel + " count: " + count);
            }

            @Override
            public void punsubscribed(String pattern, long count) {
                System.out.println("patter: " + pattern + " count: " + count);
            }
            
        });
        
        RedisPubSubCommands<String, String> sync = connect.sync();
        sync.subscribe("ch01");
    }
    void test() {
        template.convertAndSend("ch01", "hello, test pub");
    }
}
