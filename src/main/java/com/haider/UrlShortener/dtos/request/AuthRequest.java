package com.haider.UrlShortener.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = " Auth request object for login a user")
public class AuthRequest {
    @Schema(
            description = "Username for the user",
            example = "john_doe",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    @Pattern(regexp = "^[a-zA-Z0-9._]+$", message = "Username can only contain letters, numbers, dot and underscore")
    private String username;
    @Schema(
            description = "Password of the user to login",
            example = "********",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
