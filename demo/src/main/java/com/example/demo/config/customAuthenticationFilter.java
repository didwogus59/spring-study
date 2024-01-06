package com.example.demo.config;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.jwt.jwtProvider;
import com.example.demo.principal.PrincipalDetail;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class customAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
	
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException{
        System.out.println("custom authenticationFilter : 진입");
		
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
		//res.sendRedirect("/");
		chain.doFilter(req,res);
	}
}
