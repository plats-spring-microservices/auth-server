package io.github.platovd.authserver.producer;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class UserCreationStates {
    @Value("${queues.user-creation.name}")
    private String userCreationQueueName;
}