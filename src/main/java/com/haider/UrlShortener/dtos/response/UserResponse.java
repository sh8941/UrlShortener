package com.haider.UrlShortener.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response object representing a user")
public class UserResponse {

    @Schema(
            description = "Unique identifier of the user",
            example = "1"
    )
    private Long userId;

    @Schema(
            description = "Username of the user",
            example = "john_doe"
    )
    private String username;

    @Schema(
            description = "Email address of the user",
            example = "john@gmail.com"
    )
    private String email;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
