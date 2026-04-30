package com.haider.UrlShortener.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "url")
@Schema(hidden = true)
public class UrlEntity {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long urlId;
    private String longUrl;
    private String shortUrl;
    private boolean active;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity createdBy;
    private Long clickCount;

    public UrlEntity() {
        this.urlId = 0L;
        this.longUrl = null;
        this.shortUrl = null;
        this.active = true;
        this.createdBy = null;
        this.clickCount = 0L;
    }

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserEntity createdBy) {
        this.createdBy = createdBy;
    }

    public Long getClickCount() {
        return clickCount;
    }

    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }

    @Override
    public String toString() {
        return "UrlEntity{" +
                "urlId=" + urlId +
                ", longUrl='" + longUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                ", active=" + active +
                ", createdBy=" + createdBy +
                '}';
    }
}
