package com.haider.UrlShortener.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UrlRequest {
    @NotBlank
    String longUrl;

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    @Override
    public String toString() {
        return "UrlRequest{" +
                "longUrl='" + longUrl + '\'' +
                '}';
    }
}
