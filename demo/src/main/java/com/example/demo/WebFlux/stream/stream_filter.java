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

    @Override
    public void doFilter(ServletRequest request, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse servletResponse = (HttpServletResponse) res;

        servletResponse.setContentType("text/event-stream:charset=utf-8");

        PrintWriter out = servletResponse.getWriter();
        for(int i = 0; i < 5; i++) {
            out.print(" test: " + i);
            out.flush();
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
       }
       chain.doFilter(request, res);
    }
    
}
