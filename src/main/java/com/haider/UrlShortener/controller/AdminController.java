package com.haider.UrlShortener.controller;

import com.haider.UrlShortener.dtos.response.CustomPage;
import com.haider.UrlShortener.dtos.response.UrlResponse;
import com.haider.UrlShortener.dtos.response.UserResponse;
import com.haider.UrlShortener.entity.UrlEntity;
import com.haider.UrlShortener.entity.UserEntity;
import com.haider.UrlShortener.service.UrlService;
import com.haider.UrlShortener.service.UserService;
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
public class AdminController {
    @Autowired
    private UrlService urlService;
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public CustomPage<UserResponse> getUsers(@RequestParam(required = false,defaultValue = "0") Integer page,
                                             @RequestParam(required = false,defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return userService.getUsers(pageable);
    }

    @GetMapping("/urls")
    public CustomPage<UrlResponse> getUrl(@RequestParam(required = false,defaultValue = "0") Integer page,
                                          @RequestParam(required = false,defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return urlService.getUrls(pageable);
    }

}
