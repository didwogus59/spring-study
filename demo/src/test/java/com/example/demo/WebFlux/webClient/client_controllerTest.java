package com.example.demo.WebFlux.webClient;

import javax.net.ssl.SSLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
public class client_controllerTest {
    @Autowired
    client_controller controller;
    @Test
    void test_block() throws SSLException {
        for(int i = 0; i < 100; i++) {
            controller.test_block();
        }

    }

    @Test
    void test_nonblock() throws SSLException {
        for(int i = 0; i < 100; i++) {
            controller.test_nonblock();
        }
    }
}
