package com.haider.UrlShortener.dtos.request;


import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequest {
    @Size(min = 1, max = 255, message = "username size must be between 6 to 20")
    private String username;
    @Email(message = "enter correct email")
    private String email;
    @Size(min = 6, max = 20, message = "password size should be between 6 to 20")
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
