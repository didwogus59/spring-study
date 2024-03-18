package com.example.demo.WebFlux.stream;

import org.springframework.stereotype.Component;

@Component
public class simple_event {
    private boolean check = false;

    private int num = 0;

    public int get_num(){
        check = false;
        return num++;
    }

    void event_emit() {
        check = true;
    }

    boolean get_check() {
        return check;
    }
}
