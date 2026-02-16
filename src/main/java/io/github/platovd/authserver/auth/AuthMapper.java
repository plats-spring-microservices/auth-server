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

    public UserCreationMessage registrationRequestToMessage(ExtendedRegistrationRequest request) {
        return new UserCreationMessage(
                request.getId(),
                request.getUsername(),
                request.getEmail(),
                request.getFirstName(),
                request.getLastName(),
                request.getProvider().name(),
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
