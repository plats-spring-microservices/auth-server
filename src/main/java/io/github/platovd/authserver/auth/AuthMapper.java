package io.github.platovd.authserver.auth;

import org.springframework.stereotype.Component;

@Component
public class AuthMapper {
    public Auth registrationRequestToAuth(RegistrationRequest request) {
        return Auth.builder()
                .username(request.username())
                .email(request.email())
                .build();
    }
}
