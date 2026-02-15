package io.github.platovd.authserver.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExtendedRegistrationRequest {
    private String id;
    private AuthProvider provider;
    private String providerUserId;

    @NotBlank(message = "Username should be provided")
    private String username;
    @NotBlank(message = "Firstname should be provided")
    private String firstName;
    @NotBlank(message = "Lastname should be provided")
    private String lastName;
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 5, message = "Password should contain at least 5 symbols")
    private String password;
}
