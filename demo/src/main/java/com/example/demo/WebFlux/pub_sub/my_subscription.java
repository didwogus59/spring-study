package com.example.demo.WebFlux.pub_sub;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Iterator;

public class my_subscription implements Subscription {

    private Subscriber s;
    private Iterator<Integer> it;

    public my_subscription(Subscriber s, Iterable<Integer> its) {
        this.s = s;
        this.it = its.iterator();
        System.out.println("my subscription created");
    }
    @Override
    public void request(long n) {
        while(n > 0) {
            if(it.hasNext()) {
                s.onNext(it.next());
            }
            else {
                s.onComplete();
                break;
            }
        }
    }

    @Override
    public void cancel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancel'");
    }
    
}
