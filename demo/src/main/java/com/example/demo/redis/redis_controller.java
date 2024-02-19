package com.example.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class redis_controller {
    
    @Autowired
    pubsub_service broker;

    @GetMapping("/redis/test")
    public String test() {    
        broker.test();
        return "home";
    }
}
