package com.haider.UrlShortener.repo;

import com.haider.UrlShortener.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUserId(Long id);

    Optional<UserEntity> findByUsernameAndActiveTrue(String username);
    Optional<UserEntity> findByUserIdAndActiveTrue(Long id);
}
