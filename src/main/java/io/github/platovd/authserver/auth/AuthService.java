package io.github.platovd.authserver.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository repository;
    private final PasswordEncoder passwordEncoder;

    public String registerUser(RegistrationRequest request) {
        return null;
    }

    public String loginUser(LoginRequest request) {
        return null;
    }
}
