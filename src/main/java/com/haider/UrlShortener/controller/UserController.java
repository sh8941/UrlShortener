package com.haider.UrlShortener.controller;

import com.haider.UrlShortener.dtos.request.UserRequest;
import com.haider.UrlShortener.dtos.response.UserResponse;
import com.haider.UrlShortener.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
        UserResponse saved =  userService.addUser(userRequest);
        return ResponseEntity.ok().body(saved);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        UserResponse userResponse = userService.getUserById();
        return ResponseEntity.ok().body(userResponse);
    }

    @DeleteMapping("/me")
    public ResponseEntity<?> deleteUser() {
        userService.deleteUser();
        return ResponseEntity.ok().build();
    }

}
