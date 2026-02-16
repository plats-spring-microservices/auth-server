package io.github.platovd.authserver.facade;

import io.github.platovd.authserver.auth.*;
import io.github.platovd.authserver.jwt.JWTResponse;
import io.github.platovd.authserver.jwt.JWTService;
import io.github.platovd.authserver.producer.RabbitProducer;
import io.github.platovd.authserver.producer.UserCreationStates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
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
        UserCreationMessage message = authService.buildMessage(extendedRequest);
        log.info(message.toString());
        // push the event in rabbit
        producer.sendMessage(userCreationStates.getUserCreationQueueName(), message);
        // generate jwt and send response
        Auth auth = authService.getAuthById(extendedRequest.getId());
        return jwtService.generateResponseForAuth(auth);
    }

    public JWTResponse loginUser(LoginRequest request) {
        Auth auth = authService.loginUser(request);
        return jwtService.generateResponseForAuth(auth);
    }
}
