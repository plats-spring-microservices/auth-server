package io.github.platovd.authserver.facade;

import io.github.platovd.authserver.auth.*;
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
        // create and save auth entity
        ExtendedRegistrationRequest extendedRequest = authService.registerWithExtendingRequest(request);
        extendedRequest.setProvider(AuthProvider.NO_PROVIDER);
        extendedRequest.setProviderUserId(extendedRequest.getId());
        // push the event in rabbit
        producer.sendMessage(userCreationStates.getUserCreationQueueName(), authService.buildMessage(extendedRequest));
        // generate jwt and send response
        Auth auth = authService.getAuthById(extendedRequest.getId());
        return jwtService.generateResponseForAuth(auth);
    }

    public JWTResponse loginUser(LoginRequest request) {
        Auth auth = authService.loginUser(request);
        return jwtService.generateResponseForAuth(auth);
    }
}
