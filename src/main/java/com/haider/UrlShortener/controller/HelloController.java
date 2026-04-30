package com.haider.UrlShortener.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Hello controller", description = "just for api testing purpose")
public class HelloController {
    @Operation(summary = "To check server is running properly and api working")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returns Hello World! from UrlShortener"),
    })
    @GetMapping("api/hello")
    public String sayHello() {
        return "Hello World! from UrlShortener";
    }
}
