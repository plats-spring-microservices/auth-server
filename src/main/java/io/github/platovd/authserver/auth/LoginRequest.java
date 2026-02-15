package io.github.platovd.authserver.auth;

public record LoginRequest(
        String email,
        String password
) {
}
