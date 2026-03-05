package com.example.demo.proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ProxyTestTest {

    ProxyTarget target = new ProxyTargetImpl();
    ProxyTest proxy = new ProxyTest(target);

    @Test
    void testHello() {
        assertEquals(proxy.hello(), "proxy_test_impl_hello + proxy");
    }
}
