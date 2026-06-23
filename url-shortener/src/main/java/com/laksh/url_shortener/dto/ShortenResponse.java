package com.laksh.url_shortener.dto;

public class ShortenResponse {
    private String shortUrl;
    
    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public ShortenResponse(String shortUrl) {
        this.shortUrl = shortUrl;
    }

}
