package project.member.security.oauth2;


import project.member.domain.AuthProvider;

import java.util.Map;

public class GoogleResponse implements OAuth2Response {

    private final Map<String, Object> attributes;

    public GoogleResponse(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override public AuthProvider getProvider() {
        return AuthProvider.GOOGLE;
    }

    @Override public String getProviderId() {
        return String.valueOf(attributes.get("sub"));
    }

    @Override public String getEmail() {
        return String.valueOf(attributes.get("email"));
    }

    @Override public String getName() {
        return String.valueOf(attributes.get("name"));
    }
}
