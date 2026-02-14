package io.github.platovd.authserver.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistrationRequest(
        @NotBlank(message = "Username should be provided")
        String username,
        @NotBlank(message = "Firstname should be provided")
        String firstName,
        @NotBlank(message = "Lastname should be provided")
        String lastName,
        @NotBlank(message = "Email is required")
        String email,
        @NotBlank(message = "Password is required")
        @Size(min = 5, message = "Password should contain at least 5 symbols")
        String password
) {
}
