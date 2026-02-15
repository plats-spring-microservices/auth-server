package io.github.platovd.authserver.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
@RequiredArgsConstructor
@Getter
public class JWTStates {
    @Value("${jwt.public-key-expiration}")
    private Long expirationTimeSeconds;

    @Value("${jwt.private-key-expiration}")
    private Long expirationTimeSecondsRefresh;

    @Value("${spring.application.name]")
    private String issuerName;

    private final RSAPublicKey rsaPublicKey;
    private final RSAPrivateKey rsaPrivateKey;
}
