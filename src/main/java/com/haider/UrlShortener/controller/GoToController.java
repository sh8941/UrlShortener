package com.haider.UrlShortener.controller;

import com.haider.UrlShortener.Base62.Base62Util;
import com.haider.UrlShortener.dtos.response.UrlResponse;
import com.haider.UrlShortener.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

@RestController
@RequestMapping("/goto")
@Tag(name = "GoTo controller", description = "returns long url with redirect http code")
class GoToController {
    @Autowired
    private UrlService urlService;

    @Operation(summary = "To returns original long url")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Url found"),
            @ApiResponse(responseCode = "404", description = "Url not found")
    })
    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> getUrl(@PathVariable String shortUrl) {
        java.lang.String longUrl = urlService.getLongUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(longUrl))
                .build();
    }
}
