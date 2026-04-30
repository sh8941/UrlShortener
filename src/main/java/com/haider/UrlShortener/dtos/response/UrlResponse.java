package com.haider.UrlShortener.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(description = "Response object representing a shortened URL")
public class UrlResponse implements Serializable {
    @Schema(
            description = "Unique identifier of the URL",
            example = "101"
    )
    private Long urlId;

    @Schema(
            description = "Original long URL",
            example = "https://www.google.com/search?q=url+shortener"
    )
    private String longUrl;

    @Schema(
            description = "Generated short URL",
            example = "http://short.ly/abc123"
    )
    private String shortUrl;

    @Schema(
            description = "User ID who created the URL",
            example = "1"
    )
    private Long createdBy;

    public Long getUrlId() {
        return urlId;
    }

    public void setUrlId(Long urlId) {
        this.urlId = urlId;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "UrlResponse{" +
                "urlId=" + urlId +
                ", longUrl='" + longUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                ", createdBy=" + createdBy +
                '}';
    }
}
