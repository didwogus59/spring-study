package com.example.demo.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/async")
public class async_controller {
    
    @Autowired
    async_service service;
    
    @GetMapping("/test1")
    public StringBuffer test1() {
        StringBuffer buf = new StringBuffer();
        service.set_num();
        for(int i = 0; i < 20; i++) {
            service.test_async();
            buf.append(service.get_num());
            buf.append(' ');
        }
        return buf;
    }

    @GetMapping("/test2")
    public StringBuffer test2() {
        StringBuffer buf = new StringBuffer();
        service.set_num();
        for(int i = 0; i < 20; i++) {
            service.test_sync();
            buf.append(service.get_num());
            buf.append(' ');
        }
        return buf;
    }
    
    @GetMapping("/test3")
    public StringBuffer test3() {
        StringBuffer buf = new StringBuffer();
        buf.append(service.get_num());
        return buf;
    }
}
