package com.example.demo.WebFlux.stream;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class event_filter implements Filter {

    simple_event event;
    public event_filter(simple_event event) {
        this.event = event;
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        event.event_emit();
    }
    
}
