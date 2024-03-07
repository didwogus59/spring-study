package com.example.demo.async.mysql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class dbServiceTest {

    @Autowired
    async_db_service service;
    @Test
    void testCreate_data1() {
        for(int i = 0; i < 10000; i++) {
            service.create_data1(Integer.toString(i));
        }
    }

    @Test
    void testCreate_data2() {

        for(int i = 0; i < 10000; i++) {
            service.create_data2(Integer.toString(i));
        }
    }
}
