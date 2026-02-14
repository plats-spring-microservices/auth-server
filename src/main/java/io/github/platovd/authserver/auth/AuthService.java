package io.github.platovd.authserver.auth;

import com.mongodb.DuplicateKeyException;
import io.github.platovd.authserver.exception.AuthNotFoundException;
import io.github.platovd.authserver.exception.ExistingUniqInformationException;
import io.github.platovd.authserver.exception.NonLocalAuthenticationInLogin;
import io.github.platovd.authserver.exception.WrongCredentialsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthMapper mapper;

    public String registerUser(RegistrationRequest request) {
        try {
            Auth auth = mapper.registrationRequestToAuth(request);

            String encodedPassword = passwordEncoder.encode(request.password());
            auth.setPassword(encodedPassword);

            repository.save(auth);

            return auth.getId();
        } catch (DuplicateKeyException exception) {
            throw new ExistingUniqInformationException(extractDuplicateField(exception));
        }
    }

    private String extractDuplicateField(DuplicateKeyException e) {
        String message = e.getMessage();
        if (message.contains("email")) return "Email already exists";
        if (message.contains("username")) return "Username already taken";
        return "Duplicate field value";
    }

    public String loginUser(LoginRequest request) {
        Auth auth = repository.findByEmail(request.email());

        if (Objects.isNull(auth)) throw new AuthNotFoundException("Auth wasn't found with provided email");

        if (!auth.getProvider().equals(AuthProvider.NO_PROVIDER))
            throw new NonLocalAuthenticationInLogin("Please use another service for authentication");

        if (!passwordEncoder.matches(request.password(), auth.getPassword()))
            throw new WrongCredentialsException("Wrong credentials was given");

        return auth.getId();
    }
}