package com.haider.UrlShortener.dtos.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.aspectj.bridge.IMessage;

@Schema(description = "User request object for registering a new user")
public class UserRequest {
    @Schema(
            description = "Username of the user",
            example = "john_doe",
            minLength = 6,
            maxLength = 20
    )
    @NotBlank(message = "Username cannot be empty")
    @Size(min = 6, max = 20, message = "Username size must be between 6 to 20")
    private String username;

    @Schema(
            description = "User email address",
            example = "john@gmail.com"
    )
    @Email(message = "enter correct email")
    @NotBlank(message = "Email cannot be empty")
    private String email;


    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, max = 20, message = "Password size should be between 6 to 20")
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
