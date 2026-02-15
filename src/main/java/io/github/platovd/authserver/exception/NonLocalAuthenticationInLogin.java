package io.github.platovd.authserver.exception;

public class NonLocalAuthenticationInLogin extends RuntimeException {
    public NonLocalAuthenticationInLogin(String message) {
        super(message);
    }
}
