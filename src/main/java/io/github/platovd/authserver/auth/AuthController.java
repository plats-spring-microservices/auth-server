package io.github.platovd.authserver.auth;

import io.github.platovd.authserver.facade.AuthFacade;
import io.github.platovd.authserver.jwt.JWTResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthFacade authFacade;

    @PostMapping("/register")
    public ResponseEntity<JWTResponse> register(@RequestBody @Valid RegistrationRequest request) {
        return ResponseEntity.ok(authFacade.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authFacade.loginUser(request));
    }
}
