package com.example.demo.config;

import java.io.IOException;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.demo.jwt.jwtProvider;
import com.example.demo.user.user_repository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

public class jwtAuthorizationFilter extends BasicAuthenticationFilter{

    private jwtProvider jwtProvider;
    public jwtAuthorizationFilter(AuthenticationManager authenticationManager, jwtProvider jwtProvider) {
        super(authenticationManager);
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("인증 필요 요청 됨");
        req.setAttribute("name", null);
        Cookie[] cookies = req.getCookies();
        String token = null;
        for(Cookie cookie: cookies) {
            if(cookie.getName().equals("jwt"))
                token = cookie.getValue();
        }
        System.out.printf("authorization jwt: %s\n",token);
        if(token == null || jwtProvider.validateToken(token)) {
            chain.doFilter(req, res);
            return;
        }
        //security 영역에 세션으로 저장하면 권한 관리등이 편하지만 결국 jwt의 장점이 없어짐
        //Authentication auth = jwtProvider.getAuthentication(token);
        //SecurityContextHolder.getContext().setAuthentication(auth);
        //SecurityContextHolder.clearContext();
        req.setAttribute("name", jwtProvider.getAccount(token));
        chain.doFilter(req, res);
    }
}
