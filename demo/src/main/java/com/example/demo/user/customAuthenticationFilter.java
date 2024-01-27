package com.example.demo.user;

import java.io.IOException;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.example.demo.customUserDetail.CustomDetail;
import com.example.demo.jwt.jwtProvider;
import com.example.demo.mongodb_test.db_repository;
import com.example.demo.mongodb_test.test_db;
import com.example.demo.redis.redis_repository;
import com.example.demo.redis.redis_token;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class customAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
	
	redis_repository repoR;

	public customAuthenticationFilter(AuthenticationManager manager, redis_repository repoR) {
		authenticationManager = manager;
		this.repoR = repoR;
	}

	@Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException{
        System.out.printf("custom authenticationFilter : 진입\n");
		
		String name = null;
		String password = null;
		try {
			name = req.getParameter("name");
			password = req.getParameter("password");
		} catch(Exception e) {
			e.printStackTrace();
		}
		// 유저네임패스워드 토큰 생성
        UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(name, password);
		setDetails(req,authToken);

		//여기서 로그인 정보를 확인하여 로그인 성공 유무를 판별함
		Authentication auth = authenticationManager.authenticate(authToken);
		return auth;
    }

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		SecurityContextHolder.getContext().setAuthentication(authResult);	
		chain.doFilter(req,res);
	}
}

		// System.out.println("auth result principal: " + authResult.getPrincipal());
		// System.out.println("auth result credential: " + authResult.getCredentials());
		// System.out.println("auth result name: " + authResult.getName());
		// //Iterator<GrantedAuthority> iterator = authResult.getAuthorities();
		// System.out.println("auth result authority: " + authResult.getAuthorities());
		// System.out.println("auth result detail: " + authResult.getDetails());