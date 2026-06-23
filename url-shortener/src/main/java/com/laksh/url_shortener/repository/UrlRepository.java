package com.laksh.url_shortener.repository;
import org.springframework.data.jpa.repository.*;
import com.laksh.url_shortener.entity.UrlMapping;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlMapping, Long>{
    Optional<UrlMapping> findByShortCode(String shortCode);
    
}