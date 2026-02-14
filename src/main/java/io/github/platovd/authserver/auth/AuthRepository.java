package io.github.platovd.authserver.auth;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthRepository extends MongoRepository<Auth, String> {
    Auth findByEmail(String email);
}
