package com.haider.UrlShortener.mapper;

import com.haider.UrlShortener.dtos.request.UrlRequest;
import com.haider.UrlShortener.dtos.response.UrlResponse;
import com.haider.UrlShortener.entity.UrlEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UrlMapper {
    @Mapping(source = "createdBy.userId",target = "createdBy")
    @Mapping(source = "urlId",target = "urlId")
    // entity to response
    UrlResponse toDto(UrlEntity urlEntity);


    // request to entity
    UrlEntity toEntity(UrlRequest urlRequest);
}
