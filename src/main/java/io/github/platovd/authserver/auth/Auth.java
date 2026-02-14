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
    @Builder.Default
    private String providerUserId = null;

    @Indexed(unique = true)
    @Field(name = "username")
    private String username;

    @Indexed(unique = true)
    @Field(name = "email")
    private String email;

    @Field(name = "password")
    private String password;

    @Field(name = "auth_provider")
    @Builder.Default
    private AuthProvider provider = AuthProvider.NO_PROVIDER;
}
