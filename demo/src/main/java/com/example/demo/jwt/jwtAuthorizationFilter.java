package com.example.demo.jwt;

import java.io.IOException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class jwtAuthorizationFilter extends BasicAuthenticationFilter{

    private jwtProvider jwtProvider;
    public jwtAuthorizationFilter(AuthenticationManager authenticationManager, jwtProvider jwtProvider) {
        super(authenticationManager);
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        req.setAttribute("name", null);
        Cookie[] cookies = req.getCookies();
        String token = null;
        if(cookies != null) {
            for(Cookie cookie: cookies) {
                if(cookie.getName().equals("jwt"))
                    token = cookie.getValue();
            }
        }
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
