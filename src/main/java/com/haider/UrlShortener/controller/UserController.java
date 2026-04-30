package com.haider.UrlShortener.controller;

import com.haider.UrlShortener.dtos.request.UserRequest;
import com.haider.UrlShortener.dtos.response.UserResponse;
import com.haider.UrlShortener.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/user")
@Tag(name = "User controller", description = "api for create a new user or delete existing")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "To create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created"),
    })
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
        UserResponse saved =  userService.addUser(userRequest);
        return ResponseEntity.ok().body(saved);
    }

    @Operation(summary = "To fetch current user details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get current user"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        UserResponse userResponse = userService.getUserById();
        return ResponseEntity.ok().body(userResponse);
    }

    @Operation(summary = "To delete a existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User delete"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @DeleteMapping("/me")
    public ResponseEntity<?> deleteUser() {
        userService.deleteUser();
        return ResponseEntity.ok().build();
    }

}
