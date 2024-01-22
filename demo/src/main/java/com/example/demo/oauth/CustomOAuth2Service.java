package com.example.demo.oauth;

import java.util.Optional;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.demo.customUserDetail.CustomDetail;
import com.example.demo.user.user;
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
        // System.out.println("access token : " + userRequest.getAccessToken());
        System.out.println("client Registration : " + userRequest.getClientRegistration());
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        System.out.println("user : " + delegate.loadUser(userRequest));
        oauth2Info info = new googleInfo(delegate.loadUser(userRequest).getAttributes());

        Optional<user> userOp = repo.findOneByProviderIdAndProvider(info.getProviderId(), info.getProvider());
        if(info.getProvider().equals("google")) {
            System.out.println("google login");
        }
        user user;
        if(!userOp.isPresent()) {
            user = new user();
            user.setProvider(info.getProvider());
            user.setProviderId(info.getProviderId());
            user.setName(info.getName());
            user.setEmail(info.getEmail());
            repo.save(user);
        }
        else {
            user = userOp.get();
        }
        return new CustomDetail(user);
    }

    
}
