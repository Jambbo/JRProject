package com.example.jrproject.web.dto.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.autoconfigure.graphql.ConditionalOnGraphQlSchema;
@Data
public class JwtRequest {
    @NotNull(message = "Username must be not null.")
    private String username;
    @NotNull(message = "Password must be not null.")
    private String password;
}
