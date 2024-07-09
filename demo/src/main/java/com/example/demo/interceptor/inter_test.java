package com.example.demo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class inter_test implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("==============Interceptor Pre Handle==============");
        log.info("Request URI ===> " + request.getRequestURI());
        log.info("==============Interceptor Pre Handle END==============");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        
        log.info("==============Interceptor Post Handle==============");
        log.info("==============Interceptor Post Handle END==============");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
