package com.example.demo.oauth;

import java.util.Map;

public class NaverOAuth2Info extends AbstractOAuth2Info {

    protected NaverOAuth2Info(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getProvider() {
        return "naver";
    }
}
