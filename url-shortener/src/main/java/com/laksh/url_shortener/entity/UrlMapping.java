package com.laksh.url_shortener.entity;
import jakarta.persistence.*;

@Entity
public class UrlMapping {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String shortCode;
    private String url;

    public UrlMapping(){}

    public UrlMapping(String shortCode, String url){
        this.shortCode=shortCode;
        this.url=url;
    }

    public Long getID(){
        return id;
    }

     public void setShortCode(String shortCode) {
        this.shortCode=shortCode;
    }

    public String getShortCode(){
        return shortCode;
    }
    
    public String getUrl(){
        return url;
    }
}
