package com.haider.UrlShortener.Config;

import com.haider.UrlShortener.entity.UserEntity;
import com.haider.UrlShortener.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {
    @Autowired
    private UserRepo userRepo;
    public UserEntity getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepo.findByUsernameAndActiveTrue(authentication.getName()).orElseThrow(() ->
            new UsernameNotFoundException("user not found"));
    }
}
