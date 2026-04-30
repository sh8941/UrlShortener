package com.haider.UrlShortener.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;

@Schema(description = "Url request object for create short url")
public class UrlRequest {
    @Schema(
            description = "Original url that user want to convert into short url",
            example = "https://www.youtube.com/",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @URL(message = "Invalid url")
    @NotBlank(message = "url should be proper and not blank")
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
