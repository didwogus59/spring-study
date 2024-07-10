package com.example.demo.proxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class testImpl implements test{

    @Override
    public String hello() {
        log.info("proxy_test_impl_hello");
        return "proxy_test_impl_hello";
    }
    
}
