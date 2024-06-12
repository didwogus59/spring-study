package com.example.demo.WebFlux.pub_sub;

public class pub_sub_user {
    my_pub pub = new my_pub();
    my_sub sub = new my_sub();

    void test() {
        pub.subscribe(sub);
    }
}
