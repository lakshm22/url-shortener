package com.laksh.url_shortener.controller;
import com.laksh.url_shortener.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.laksh.url_shortener.dto.ShortenRequest;
import com.laksh.url_shortener.dto.ShortenResponse;

@RestController
public class UrlController {
    private final UrlService urlService;
    public UrlController(UrlService urlService){
        this.urlService=urlService;
    }

    @GetMapping("/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code){
        String origUrl = urlService.getOriginalUrl(code);
        if(origUrl == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(302)
        .header("Location", origUrl)
        .build();
    }

    @PostMapping("/api/v1/urls")
    public ShortenResponse shorten(@RequestBody ShortenRequest request){
       String shortCode = urlService.shortenUrl(request.getUrl());
       return new ShortenResponse(shortCode);
    }
}