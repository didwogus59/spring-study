package com.example.demo.oauth;

import java.util.Map;
import java.util.Optional;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.demo.customUserDetail.CustomDetail;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuth2Service implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{
    private final UserRepository repo;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest : " + userRequest);
        System.out.println("access token : " + userRequest.getAccessToken());
        System.out.println("client Registration : " + userRequest.getClientRegistration());
        return processOauth2User(userRequest);
    }

    private OAuth2User processOauth2User(OAuth2UserRequest userRequest) {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        AbstractOAuth2Info info = null;
        String registration = userRequest.getClientRegistration().getRegistrationId();
        if(registration.equals("google")) {

            info = new GoogleOAuth2Info(delegate.loadUser(userRequest).getAttributes());
        }
        else if(registration.equals("naver")) {
            info = new NaverOAuth2Info((Map)delegate.loadUser(userRequest).getAttributes().get("response"));
        }
        else {
            System.out.printf("please login by google or naver\n");
        }

        Optional<User> userOp = repo.findOneByProviderIdAndProvider(info.getProviderId(), info.getProvider());
        User user;
        if(!userOp.isPresent()) {
            user = new User();
            user.setProvider(info.getProvider());
            user.setProviderId(info.getProviderId());
            user.setName(info.getName());
            user.setEmail(info.getEmail());
            user.setRole("ROLE_USER");
            repo.save(user);
        }
        else {
            System.out.printf("user is exist\n");
            user = userOp.get();
        }
        return new CustomDetail(user);
    }
}
