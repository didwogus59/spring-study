package com.example.demo.oauth;

import java.util.Map;

public class naverInfo implements oauth2Info{
     private Map<String, Object> attributes;

    naverInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
    
    @Override
    public String getProviderId() {
        return (String) attributes.get("id");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

	@Override
	public String getProvider() {
		return "naver";
	}
}
