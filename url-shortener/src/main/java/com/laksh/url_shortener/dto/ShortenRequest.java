package com.laksh.url_shortener.dto;

public class ShortenRequest {
    private String url;
    
    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url=url;
    }

    public ShortenRequest(String url){
        this.url=url;
    }
}
