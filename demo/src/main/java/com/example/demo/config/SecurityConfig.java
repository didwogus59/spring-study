package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.jwt.jwtAuthenticationFilter;
import com.example.demo.jwt.jwtAuthorizationFilter;
import com.example.demo.jwt.jwtProvider;
import com.example.demo.oauth.CustomOAuth2Service;
import com.example.demo.redis.redis_repository;
import com.example.demo.user.customAuthenticationFilter;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private CorsConfig corsConfig;
    
    @Autowired
    private CustomOAuth2Service customoAuth2Service;
    
    @Autowired
    private final jwtProvider tokenProvider;

    @Autowired
    redis_repository repoR;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //http.addFilterBefore(new myfilter1(),  BasicAuthenticationFilter.class);
        http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
            .requestMatchers(new AntPathRequestMatcher("/user/data"))
            .hasRole("User")
            .anyRequest().permitAll());
      
        http.httpBasic((basic) -> basic.disable());

        http.apply(new jwtDsl(tokenProvider));
        http.apply(new sessionDsl());

        http.logout((logout) -> logout
            .logoutUrl("/user/logout/session")
            .logoutSuccessUrl("/"));

        http.sessionManagement((session) ->
            session
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

        http.csrf((csrf) -> csrf
            .ignoringRequestMatchers(new AntPathRequestMatcher("/form/**")));

        // http.csrf((csrf) -> csrf.disable());

        http.headers((headers) -> headers
            .addHeaderWriter(new XFrameOptionsHeaderWriter(
                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)));

        http.oauth2Login((oauth) -> oauth
            .loginPage("/")
            .userInfoEndpoint((userInfoEndpoint) -> userInfoEndpoint
                .userService(customoAuth2Service)));
                
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    public class jwtDsl extends AbstractHttpConfigurer<jwtDsl, HttpSecurity> {

        private final jwtProvider jwtProvider;

        public jwtDsl(jwtProvider jwtProvider) {
            this.jwtProvider = jwtProvider;
        }
		@Override
		public void configure(HttpSecurity http) throws Exception {
			AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            jwtAuthenticationFilter filter = new jwtAuthenticationFilter(authenticationManager, jwtProvider);
            filter.setFilterProcessesUrl("/user/login/jwt/auth");
			http
            .addFilter(new jwtAuthorizationFilter(authenticationManager, jwtProvider))
            .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		}
	}

    public class sessionDsl extends AbstractHttpConfigurer<sessionDsl, HttpSecurity> {
		@Override
		public void configure(HttpSecurity http) throws Exception {
			AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            customAuthenticationFilter filter = new customAuthenticationFilter(authenticationManager, repoR);
            filter.setFilterProcessesUrl("/user/login/session/auth");
            http.
            addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		}
	}
}