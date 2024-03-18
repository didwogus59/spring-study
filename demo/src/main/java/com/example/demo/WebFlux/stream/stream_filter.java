package com.example.demo.WebFlux.stream;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

public class stream_filter implements Filter{

    simple_event event;
    stream_filter(simple_event event) {
        this.event = event;
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse servletResponse = (HttpServletResponse) res;

        servletResponse.setContentType("text/event-stream:charset=utf-8");

        PrintWriter out = servletResponse.getWriter();
        for(int i = 0; i < 5; i++) {
            out.println("test: " + i);
            out.flush();
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
       }

        while(true) {
            try {
                Thread.sleep(1);
                while(event.get_check()) {
                    int num = event.get_num();
                    out.println("new event: " + num);
                    out.flush();
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
       }
    }
    
}
