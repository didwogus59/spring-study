package com.example.demo.proxy;

public class ProxyTest implements ProxyTarget {

    private final ProxyTarget target;

    public ProxyTest(ProxyTarget target) {
        this.target = target;
    }

    @Override
    public String hello() {
        return target.hello() + " + proxy";
    }

}
