package io.github.platovd.authserver.auth;

import io.github.platovd.authserver.producer.RabbitProducer;
import io.github.platovd.authserver.producer.UserCreationStates;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserCreationStates userCreationStates;
    private final RabbitProducer producer;
}
