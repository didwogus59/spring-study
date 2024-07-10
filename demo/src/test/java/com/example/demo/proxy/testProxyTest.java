package com.example.demo.proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class testProxyTest {

    test test = new testProxy();
    @Test
    void testHello() {
        assertEquals(test.hello(), "proxy_test_impl_hello + proxy");
    }
}
