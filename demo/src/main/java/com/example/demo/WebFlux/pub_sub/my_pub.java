package com.example.demo.WebFlux.pub_sub;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.Arrays;

public class my_pub implements Publisher<Integer> {

    Iterable<Integer> its = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
    @Override
    public void subscribe(Subscriber<? super Integer> s) {
        System.out.println("test pub!");
        my_subscription subscription = new my_subscription(s, its);
        System.out.println("subscription created!");
        s.onSubscribe(subscription);
        System.out.println("on subscribe");
    }
    
}
