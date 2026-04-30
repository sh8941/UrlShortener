package com.haider.UrlShortener.controller;

import com.haider.UrlShortener.dtos.response.CustomPage;
import com.haider.UrlShortener.dtos.response.UrlResponse;
import com.haider.UrlShortener.dtos.response.UserResponse;
import com.haider.UrlShortener.entity.UrlEntity;
import com.haider.UrlShortener.entity.UserEntity;
import com.haider.UrlShortener.service.UrlService;
import com.haider.UrlShortener.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin controller", description = "api for Admin")
public class AdminController {
    @Autowired
    private UrlService urlService;
    @Autowired
    private UserService userService;

    @Operation(summary = "Api for return details of all existing users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return Existing User list"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/users")
    public CustomPage<UserResponse> getUsers(@RequestParam(required = false,defaultValue = "0") Integer page,
                                             @RequestParam(required = false,defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return userService.getUsers(pageable);
    }

    @Operation(summary = "Api for return details of all existing url")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return Existing url list"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/urls")
    public CustomPage<UrlResponse> getUrl(@RequestParam(required = false,defaultValue = "0") Integer page,
                                          @RequestParam(required = false,defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return urlService.getUrls(pageable);
    }

}
