package com.haider.UrlShortener.mapper;

import com.haider.UrlShortener.dtos.request.UserRequest;
import com.haider.UrlShortener.dtos.response.UserResponse;
import com.haider.UrlShortener.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {


    public UserResponse toDto(UserEntity userEntity);

    @Mapping(target = "password", ignore = true)
    public UserEntity toEntity(UserRequest userRequest);
}
