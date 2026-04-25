package com.haider.UrlShortener.service;

import com.haider.UrlShortener.Config.SecurityUtils;
import com.haider.UrlShortener.dtos.request.UserRequest;
import com.haider.UrlShortener.dtos.response.CustomPage;
import com.haider.UrlShortener.dtos.response.UserResponse;
import com.haider.UrlShortener.entity.UserEntity;
import com.haider.UrlShortener.mapper.UserMapper;
import com.haider.UrlShortener.repo.RoleRepo;
import com.haider.UrlShortener.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private SecurityUtils securityUtils;

    public UserResponse addUser(UserRequest userRequest) {

        // request to entity
        UserEntity userEntity = userMapper.toEntity(userRequest);
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userEntity.setActive(true);
        userEntity.setRole(roleRepo.findById(2L).orElseThrow());

        // save and fetch
        UserEntity saved = userRepo.save(userEntity);

        // entity to response
        return userMapper.toDto(saved);
    }

    public UserResponse getUserById() {
        // fetch entity
        UserEntity userEntity = securityUtils.getCurrentUser();
        // entity to response
        return userMapper.toDto(userEntity);
    }

    public UserEntity getUserEntityById(Long id) {
        return userRepo.findByUserId(id);
    }

    public void deleteUser() {
        UserEntity userEntity = securityUtils.getCurrentUser();
        userEntity.setActive(false);
    }

    public CustomPage<UserResponse> getUsers(Pageable pageable) {
        if (! securityUtils.getCurrentUser().getRole().getRoleName().equals("ADMIN")) {
            throw new AccessDeniedException( "You are not allowed to access this resource");
        }

        Page<UserEntity> page = userRepo.findAll(pageable);
        CustomPage<UserResponse> customPage = new CustomPage<>();

        List<UserResponse> dtosList = page.getContent().stream()
                .map(userEntity -> userMapper.toDto(userEntity))
                .toList();

        customPage.setContent(dtosList);
        customPage.setPage(page.getNumber() + 1);
        customPage.setSize(page.getNumberOfElements());
        customPage.setHasNext(page.hasNext());

        return customPage;
    }
}
