package com.example.demo.proxy;

public class TestProxy implements Test {
    private Test test;
    
    @Override
    public String hello() {
        if(test == null) {
            test = new TestImpl();
        }
        return test.hello() + " + proxy";
    }
}
