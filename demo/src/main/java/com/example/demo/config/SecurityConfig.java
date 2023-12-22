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
    private final user_repository user_repository;
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //http.addFilterBefore(new myfilter1(),  BasicAuthenticationFilter.class);
        http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
            .requestMatchers(new AntPathRequestMatcher("/user/data")).authenticated()
            .anyRequest().permitAll());
        //session login
        // http.formLogin((form_login) -> form_login
        //     .loginPage("/user/login/session")
        //     .loginProcessingUrl("/user/loginPR")
        //     .usernameParameter("name")
        //     .passwordParameter("password")
        //     .defaultSuccessUrl("/"));
        
        http.formLogin((form_login) -> form_login.disable());
        
        http.httpBasic((http_basic)-> http_basic.disable());

        http.apply(new MyCustomDsl());

        http.sessionManagement((session) ->
            session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

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

    public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
		@Override
		public void configure(HttpSecurity http) throws Exception {
			AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
			http
            .addFilter(new jwtAuthenticationFilter(authenticationManager, tokenProvider))
            .addFilter(new jwtAuthorizationFilter(authenticationManager, tokenProvider));
		}
	}
}