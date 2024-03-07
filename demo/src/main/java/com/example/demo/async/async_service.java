package com.example.demo.async;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class async_service {
    
    static int num = 0;
    @Async
    void test_async() {
        num += 1;
    }

    void test_sync() {
        num += 1;
    }

    void set_num() {
        num = 0;
    }

    int get_num() {
        return num;
    }
}
