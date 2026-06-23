package com.laksh.url_shortener.service;
import org.springframework.stereotype.*;
import com.laksh.url_shortener.repository.*;
import com.laksh.url_shortener.entity.*;
import com.laksh.url_shortener.utility.*;

@Service
public class UrlService {
    private final UrlRepository urlRepository;
    public UrlService(UrlRepository urlRepository){
        this.urlRepository=urlRepository;
    }

    public String shortenUrl(String origUrl){
        UrlMapping mapping = new UrlMapping(null, origUrl);
        urlRepository.save(mapping);
        String shortCode=Base62Encoder.encode(mapping.getID());
        mapping.setShortCode(shortCode);
        urlRepository.save(mapping);
        return shortCode;
    }
    public String getOriginalUrl(String shortCode){
        UrlMapping mapping = urlRepository
        .findByShortCode(shortCode)
        .orElse(null);
        if(mapping==null) return null;
        return mapping.getUrl();
    }
}
