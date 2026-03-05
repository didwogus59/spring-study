package com.example.demo.oauth;

import java.util.Map;

public class GoogleOAuth2Info extends AbstractOAuth2Info {

    protected GoogleOAuth2Info(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getProvider() {
        return "google";
    }
}
