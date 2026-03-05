package com.example.demo.oauth;

import java.util.Map;

public abstract class AbstractOAuth2Info implements oauth2Info {

    protected Map<String, Object> attributes;

    protected AbstractOAuth2Info(Map<String, Object> attributes) {
        this.attributes = attributes;
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
    public String getProviderId() {
        return (String) attributes.get("id");
    }
}
