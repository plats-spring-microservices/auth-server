package io.github.platovd.authserver.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.github.platovd.authserver.auth.Auth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class JWTService {
    private final JWTStates states;

    private final Algorithm algorithm;
    private final JWTVerifier verifier;

    private static final Map<String, Object> headerMap = Map.of(
            "typ", "JWT"
    );

    public String createAccessToken(Auth auth) {
        return JWT.create()
                .withHeader(headerMap)
                .withIssuer(states.getIssuerName())
                .withSubject(auth.getId())
                .withClaim("email", auth.getEmail())
                .withClaim("type", "access")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + states.getExpirationTimeSeconds() * 1000))
                .sign(algorithm);
    }

    public String createRefreshToken(Auth auth) {
        return JWT.create()
                .withHeader(headerMap)
                .withIssuer(states.getIssuerName())
                .withSubject(auth.getId())
                .withClaim("type", "refresh")
                .withClaim("email", auth.getEmail())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + states.getExpirationTimeSecondsRefresh() * 1000))
                .sign(algorithm);
    }

    public DecodedJWT verifyAnyToken(String token) {
        return verifier.verify(token);
    }

    public DecodedJWT verifyTokenWithType(String token, String type) {
        DecodedJWT decoded = verifyAnyToken(token);
        if (!isTypeOf(decoded, type)) throw new JWTVerificationException("Wrong token type. It is not " + type);
        return decoded;
    }

    public <T> T extractClaim(String token, String claimName, Class<T> claimClass) {
        DecodedJWT decoded = verifyAnyToken(token);
        T claimValue = decoded.getClaim(claimName).as(claimClass);
        if (Objects.isNull(claimValue)) throw new IllegalArgumentException("Trying to extract the claim doesn't exist");
        return claimValue;
    }

    public String extractUserId(String token) {
        DecodedJWT decoded = verifyAnyToken(token);
        String subject = decoded.getSubject();
        if (Objects.isNull(subject)) throw new JWTVerificationException("Unknown subject of token");
        return subject;
    }

    public String extractEmail(String token) {
        return extractClaim(token, "email", String.class);
    }

    public String extractTokenType(String token) {
        return extractClaim(token, "type", String.class);
    }

    public boolean isTypeOf(String token, String type) {
        return extractTokenType(token).equals(type);
    }

    public boolean isTypeOf(DecodedJWT decodedToken, String type) {
        return Objects.equals(decodedToken.getClaim("type").as(String.class), type);
    }

    public JWTResponse generateResponseForAuth(Auth auth) {
        return new JWTResponse(
                createAccessToken(auth),
                createRefreshToken(auth),
                "Bearer",
                states.getExpirationTimeSeconds(),
                states.getExpirationTimeSecondsRefresh()
        );
    }
}
