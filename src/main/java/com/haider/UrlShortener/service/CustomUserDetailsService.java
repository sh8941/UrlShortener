package com.haider.UrlShortener.service;

import com.haider.UrlShortener.entity.UserEntity;
import com.haider.UrlShortener.repo.UserRepo;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findByUsernameAndActiveTrue(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isActive(),
                true,true,true,  // accountNonExpired, credentialsNonExpired, accountNonLocked
                AuthorityUtils.createAuthorityList("ROLE_"+userEntity.getRole().getRoleName())
        );
    }
}
