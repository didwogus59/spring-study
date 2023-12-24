package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.jwt.jwtProvider;
import com.example.demo.user.user_repository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private CorsConfig corsConfig;

    private final jwtProvider tokenProvider;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //http.addFilterBefore(new myfilter1(),  BasicAuthenticationFilter.class);
        http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
            .requestMatchers(new AntPathRequestMatcher("/user/data")).authenticated()
            .anyRequest().permitAll());
                
        http.httpBasic((http_basic)-> http_basic.disable());
      
        http.formLogin((form_login) -> form_login
            .loginPage("/user/login/session")
            .usernameParameter("name")
            .passwordParameter("password")
            .loginProcessingUrl("/user/login/session")
            .defaultSuccessUrl("/"));

        http.apply(new sessionDsl());
        http.apply(new jwtDsl());

        http.sessionManagement((session) ->
            session
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

        http.csrf((csrf) -> csrf
            .ignoringRequestMatchers(new AntPathRequestMatcher("/**")));

        // http.headers((headers) -> headers
        //     .addHeaderWriter(new XFrameOptionsHeaderWriter(
        //         XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)));
                
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public class jwtDsl extends AbstractHttpConfigurer<jwtDsl, HttpSecurity> {
		@Override
		public void configure(HttpSecurity http) throws Exception {
			AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
			http
            .addFilter(new jwtAuthorizationFilter(authenticationManager, tokenProvider));
		}
	}

    public class sessionDsl extends AbstractHttpConfigurer<sessionDsl, HttpSecurity> {
		@Override
		public void configure(HttpSecurity http) throws Exception {
			AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            http.addFilterBefore(new customAuthenticationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class);
		}
	}
}