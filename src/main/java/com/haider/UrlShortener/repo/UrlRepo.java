package com.haider.UrlShortener.repo;

import com.haider.UrlShortener.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepo extends JpaRepository<UrlEntity, Long> {
    Optional<UrlEntity> findByUrlIdAndActiveTrue(Long id);
}
