package com.haider.UrlShortener.service;

import com.haider.UrlShortener.Base62.Base62Util;
import com.haider.UrlShortener.Config.SecurityUtils;
import com.haider.UrlShortener.Config.UrlAuthorization;
import com.haider.UrlShortener.dtos.request.UrlRequest;
import com.haider.UrlShortener.dtos.response.CustomPage;
import com.haider.UrlShortener.dtos.response.UrlResponse;
import com.haider.UrlShortener.entity.UrlEntity;
import com.haider.UrlShortener.entity.UserEntity;
import com.haider.UrlShortener.exception.ResourceNotFound;
import com.haider.UrlShortener.mapper.UrlMapper;
import com.haider.UrlShortener.repo.UrlRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UrlService {
    @Autowired
    private UrlRepo urlRepo;
    @Autowired
    private UrlMapper urlMapper;
    @Autowired
    private SecurityUtils securityUtils;
    @Autowired
    private UrlAuthorization urlAuthorization;

    public UrlResponse addUrl(UrlRequest urlRequest) {

        // request to entity
        UrlEntity urlEntity = urlMapper.toEntity(urlRequest);
        // set active and use to the entity
        urlEntity.setCreatedBy(securityUtils.getCurrentUser());
        urlEntity.setActive(true);
        // set user
        UserEntity userEntity = securityUtils.getCurrentUser();
        urlEntity.setCreatedBy(userEntity);

        UrlEntity savedEntity = urlRepo.save(urlEntity);

        // encode and save with short url
        String shortUrl = Base62Util.encode(savedEntity.getUrlId());
        urlEntity.setShortUrl(shortUrl);
        UrlEntity finalSaved = urlRepo.save(urlEntity);

        return urlMapper.toDto(finalSaved);
    }

    @Cacheable(value = "url", key = "#shortUrl")
    public String getLongUrl(String shortUrl) {
        // decode shortUrl then go to index, then fetch and return
        System.out.println("    fetching from database...");
        Long urlId = Base62Util.decode(shortUrl);
        UrlEntity urlEntity = urlRepo.findByUrlIdAndActiveTrue(urlId).orElseThrow(() ->
                new ResourceNotFound("Url not found with id " + shortUrl));
        return urlEntity.getLongUrl();
    }

    @CacheEvict(value = "url", key = "#shortUrl")
    public void deleteUrl(String shortUrl) {
        // decode shortUrl and fetch entity
        Long urlId = Base62Util.decode(shortUrl);
        UrlEntity urlEntity = urlRepo.findByUrlIdAndActiveTrue(urlId).orElseThrow(() ->
                new ResourceNotFound("Url not found with id " + shortUrl));
        // current user
        UserEntity userEntity = securityUtils.getCurrentUser();

        // authorization
        if (urlAuthorization.isOwnedBy(userEntity, urlEntity) ||
            urlAuthorization.isAdmin(userEntity)) {
            // set active to false for soft delete
            urlEntity.setActive(false);
            urlRepo.save(urlEntity);
        } else {
            throw new AuthorizationDeniedException("You are not allowed to access this resource");
        }

    }

    public CustomPage<UrlResponse> getUrls(Pageable pageable) {
        if (! securityUtils.getCurrentUser().getRole().getRoleName().equals("ADMIN")) {
            throw new AccessDeniedException( "You are not allowed to access this resource");
        }

        CustomPage<UrlResponse> customPage = new CustomPage<>();
        Page<UrlEntity> page = urlRepo.findAll(pageable);

        List<UrlResponse> dtosList = page.getContent().stream()
                        .map(url -> urlMapper.toDto(url))
                                .toList();
        customPage.setContent(dtosList);
        customPage.setPage(page.getNumber());
        customPage.setSize(page.getNumberOfElements());
        customPage.setHasNext(page.hasNext());

        return customPage;
    }
}
