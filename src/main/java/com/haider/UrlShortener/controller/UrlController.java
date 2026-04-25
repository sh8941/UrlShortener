package com.haider.UrlShortener.controller;

import com.haider.UrlShortener.dtos.request.UrlRequest;
import com.haider.UrlShortener.dtos.response.UrlResponse;
import com.haider.UrlShortener.service.UrlService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/url")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping
    public ResponseEntity<?> createUrl(@Valid @RequestBody UrlRequest urlRequest) {
        UrlResponse urlResponse = urlService.addUrl(urlRequest);
        return new ResponseEntity<>(urlResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{shortUrl}")
    public ResponseEntity<?> deleteUrl(@PathVariable String shortUrl) {
        urlService.deleteUrl(shortUrl);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
