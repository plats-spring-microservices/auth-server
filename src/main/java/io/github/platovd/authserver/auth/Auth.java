package io.github.platovd.authserver.auth;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Auth {
    @Id
    private String id;

    @Indexed(unique = true)
    @Field(name = "provider_user_id")
    private String providerUserId;

    @Indexed(unique = true)
    @Field(name = "email")
    private String email;

    @Field(name = "password")
    private String password;

    @Field(name = "auth_provider")
    private AuthProvider provider;
}
