package io.github.platovd.authserver.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(String routing, Object data) {
        rabbitTemplate.convertAndSend(routing, data);
    }
}
