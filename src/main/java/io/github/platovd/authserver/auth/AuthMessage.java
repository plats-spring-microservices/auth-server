package io.github.platovd.authserver.auth;

public record AuthMessage(
        String id,

        String username,

        String email,

        String firstName,

        String lastName,

        AuthProvider provider,

        String providerUserId
) {
}
