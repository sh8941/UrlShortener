package com.haider.UrlShortener.service;

import com.haider.UrlShortener.UrlShortenerApplication;
import org.hibernate.validator.internal.constraintvalidators.bv.NotNullValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = UrlShortenerApplication.class)
public class CustomUserDetailsServiceTest {
    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    public void test(){
        UserDetails userDetails = userDetailsService.loadUserByUsername("thor");
        System.out.println("userDetails = " + userDetails);
        assertNotNull(userDetails);
    }

}
