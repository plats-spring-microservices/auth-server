package io.github.platovd.authserver.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
public class JWTConfig {
    @Value("${jwt.public-key}")
    private String rsaPublicKey;

    @Value("${jwt.private-key}")
    private String rsaPrivateKey;

    @Bean
    public RSAPublicKey rsaPublicKey() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String publicKeyContent = rsaPublicKey
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");

        byte[] keyBytes = Base64.getDecoder().decode(publicKeyContent);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) keyFactory.generatePublic(spec);
    }

    @Bean
    public RSAPrivateKey rsaPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String privateKeyContent = rsaPrivateKey
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        byte[] keyBytes = Base64.getDecoder().decode(privateKeyContent);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) keyFactory.generatePrivate(spec);
    }

    @Bean
    public Algorithm jwtEncodingAlgorithm() throws NoSuchAlgorithmException, InvalidKeySpecException {
        return Algorithm.RSA256(rsaPublicKey(), rsaPrivateKey());
    }

    @Bean
    public JWTVerifier jwtVerifier() throws NoSuchAlgorithmException, InvalidKeySpecException {
        return JWT.require(jwtEncodingAlgorithm()).build();
    }
}
