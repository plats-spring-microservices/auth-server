package io.github.platovd.authserver.auth;

import org.springframework.stereotype.Component;

@Component
public class AuthMapper {
    public Auth registrationRequestToAuth(RegistrationRequest request) {
        return Auth.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .build();
    }

    public AuthMessage registrationRequestToMessage(ExtendedRegistrationRequest request) {
        return new AuthMessage(
                request.getId(),
                request.getUsername(),
                request.getEmail(),
                request.getFirstName(),
                request.getLastName(),
                request.getProvider(),
                request.getProviderUserId()
        );
    }

    public ExtendedRegistrationRequest registrationRequestToExtendedVersion(RegistrationRequest request) {
        return ExtendedRegistrationRequest.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(request.getPassword())
                .build();
    }
}
