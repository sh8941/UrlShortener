package com.haider.UrlShortener.controller;

import com.haider.UrlShortener.dtos.request.UrlRequest;
import com.haider.UrlShortener.dtos.response.UrlResponse;
import com.haider.UrlShortener.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/url")
@Tag(name = "Url controller", description = "api for create a new short url or delete existing")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @Operation(summary = "To create and return a short url")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Url created"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping
    public ResponseEntity<?> createUrl(@Valid @RequestBody UrlRequest urlRequest) {
        UrlResponse urlResponse = urlService.addUrl(urlRequest);
        return new ResponseEntity<>(urlResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "To delete a existing url")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "deleted"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{shortUrl}")
    public ResponseEntity<?> deleteUrl(@PathVariable String shortUrl) {
        urlService.deleteUrl(shortUrl);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
