package com.example.demo.WebFlux.pub_sub;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class my_sub implements Subscriber<Integer> {

    private Subscription s;
    private int buffer_size = 2;
    @Override
    public void onSubscribe(Subscription s) {
        this.s = s;
        System.out.println("sub data delivered");
        s.request(buffer_size);
    }

    @Override
    public void onNext(Integer t) {
        buffer_size--;
        System.out.printf("onNext: %d, buffer size: %d\n", t, buffer_size);
        if(buffer_size == 0) {
            System.out.printf("no buffer\n", t);
            buffer_size = 2;
            s.request(buffer_size);
        }
    }

    @Override
    public void onError(Throwable t) {
        System.out.println("error");
    }

    @Override
    public void onComplete() {
        System.out.println("my_sub completed");
    }
    
}
