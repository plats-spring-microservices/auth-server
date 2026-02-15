package io.github.platovd.authserver.jwt;

public record JWTResponse(
        String accessToken,
        String refreshToken,
        String tokenType,
        Long expiresIn,
        Long refreshExpiresIn
) {
}
