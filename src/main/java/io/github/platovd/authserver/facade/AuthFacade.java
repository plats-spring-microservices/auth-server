package io.github.platovd.authserver.facade;

import io.github.platovd.authserver.auth.AuthService;
import io.github.platovd.authserver.auth.LoginRequest;
import io.github.platovd.authserver.auth.RegistrationRequest;
import io.github.platovd.authserver.jwt.JWTResponse;
import io.github.platovd.authserver.jwt.JWTService;
import io.github.platovd.authserver.producer.RabbitProducer;
import io.github.platovd.authserver.producer.UserCreationStates;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthFacade {
    private final UserCreationStates userCreationStates;
    private final RabbitProducer producer;

    private final AuthService authService;
    private final JWTService jwtService;

    public JWTResponse registerUser(RegistrationRequest request) {
        return null;
    }

    public JWTResponse loginUser(LoginRequest request) {
        return null;
    }
}
