package com.example.demo.proxy;

public class testProxy implements test{

    test test;
    @Override
    public String hello() {
        if(test == null) {
            test = new testImpl();
        }
        return test.hello() + " + proxy";
    }
    
}
