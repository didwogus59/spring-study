package com.example.demo.proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestProxyTest {

    Test test = new TestProxy();
    @Test
    void testHello() {
        assertEquals(test.hello(), "proxy_test_impl_hello + proxy");
    }
}
