package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.jwt.jwtProvider;
import com.example.demo.principal.PrincipalDetailsService;
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
    
    
    @Autowired
    private final jwtProvider tokenProvider;

    @Autowired
    private PrincipalDetailsService principalDetailsService;
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //http.addFilterBefore(new myfilter1(),  BasicAuthenticationFilter.class);
        http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
            .requestMatchers(new AntPathRequestMatcher("/user/data")).authenticated()
            .anyRequest().permitAll());
      
        
        http.httpBasic((basic) -> basic.disable());
        http.apply(new jwtDsl(tokenProvider));
        http.apply(new sessionDsl());
        // http.formLogin((form_login) -> form_login
        //     .loginProcessingUrl("/user/login/jwt")
        //     .usernameParameter("name")
        //     .passwordParameter("password")
        //     .defaultSuccessUrl("/"));


        http.sessionManagement((session) ->
            session
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

        http.csrf((csrf) -> csrf
            .ignoringRequestMatchers(new AntPathRequestMatcher("/form/**")));

        http.headers((headers) -> headers
            .addHeaderWriter(new XFrameOptionsHeaderWriter(
                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)));

        
                
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // @Override 
    // void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.userDetailsService(principalDetailsService);
    // }

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     return principalDetailsService;
    // }
    
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
            .addFilter(corsConfig.corsFilter())
            .addFilter(new jwtAuthorizationFilter(authenticationManager, jwtProvider))
            .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		}
	}

    public class sessionDsl extends AbstractHttpConfigurer<sessionDsl, HttpSecurity> {
		@Override
		public void configure(HttpSecurity http) throws Exception {
			AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            customAuthenticationFilter filter = new customAuthenticationFilter(authenticationManager);
            filter.setFilterProcessesUrl("/user/login/session/auth");
            http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		}
	}
}


// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Bean
//     public SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception {
//         http.authorizeHttpRequests((authz) -> authz
//                 .anyRequest().authenticated());
//         http.addFilterBefore(apiAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//         return http.build();
//     }

//     private AuthenticationFilter apiAuthenticationFilter() {
//         AuthenticationFilter authenticationFilter = new AuthenticationFilter(new ApiAuthenticationManagerResolver(), new BasicAuthenticationConverter());
//         authenticationFilter.setSuccessHandler((request, response, authentication) -> {});
//         return authenticationFilter;
//     }

//     public static class ApiAuthenticationManagerResolver implements AuthenticationManagerResolver<HttpServletRequest> {

//         private final Map<RequestMatcher, AuthenticationManager> managers = Map.of(
//                 new AntPathRequestMatcher("/dog/**"), new DogAuthenticationProvider()::authenticate,
//                 new AntPathRequestMatcher("/cat/**"), new CatAuthenticationProvider()::authenticate
//         );

//         @Override
//         public AuthenticationManager resolve(HttpServletRequest request) {
//             for (Map.Entry<RequestMatcher, AuthenticationManager> entry : managers.entrySet()) {
//                 if (entry.getKey().matches(request)) {
//                     return entry.getValue();
//                 }
//             }
//             throw new IllegalArgumentException("Unable to resolve AuthenticationManager");
//         }
//     }

//     public static class DogAuthenticationProvider implements AuthenticationProvider {

//         @Override
//         public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//             if (authentication.getName().endsWith("_dog")) {
//                 return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(),
//                         AuthorityUtils.createAuthorityList("ROLE_DOG"));
//             }
//             throw new BadCredentialsException("Username should end with _dog");
//         }

//         @Override
//         public boolean supports(Class<?> authentication) {
//             return true;
//         }

//     }

//     public static class CatAuthenticationProvider implements AuthenticationProvider {

//         @Override
//         public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//             if (authentication.getName().endsWith("_cat")) {
//                 return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(),
//                         AuthorityUtils.createAuthorityList("ROLE_CAT"));
//             }
//             throw new BadCredentialsException("Username should end with _cat");
//         }

//         @Override
//         public boolean supports(Class<?> authentication) {
//             return true;
//         }

// @Bean
// public SecurityFilterChain dogApiSecurity(HttpSecurity http) throws Exception {
//     http.requestMatchers((matchers) -> matchers
//                 .antMatchers("/dog/**"));
//     http.authorizeRequests((authz) -> authz
//             .anyRequest().authenticated());
//     http.httpBasic();
//     http.authenticationProvider(new DogAuthenticationProvider());
//     return http.build();
// }

// @Bean
// public SecurityFilterChain catApiSecurity(HttpSecurity http) throws Exception {
//     http.requestMatchers((matchers) -> matchers
//                 .antMatchers("/cat/**"));
//     http.authorizeRequests((authz) -> authz
//             .anyRequest().authenticated());
//     http.httpBasic();
//     http.authenticationProvider(new CatAuthenticationProvider());
//     return http.build();
// }