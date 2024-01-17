package com.example.demo.oauth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.demo.user.user_repository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuth2Service implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{
    private final user_repository repo;
    

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest : " + userRequest);
        System.out.println("access token : " + userRequest.getAccessToken());
        System.out.println("userRequest : " + userRequest.getClientRegistration());
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User user = delegate.loadUser(userRequest);
        System.out.println("user : " + user);
        // String reig
        return user;
    }

    
}
