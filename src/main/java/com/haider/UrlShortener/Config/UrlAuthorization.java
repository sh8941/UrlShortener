package com.haider.UrlShortener.Config;

import com.haider.UrlShortener.entity.UrlEntity;
import com.haider.UrlShortener.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UrlAuthorization {

    public boolean isAdmin(UserEntity user) {
        return user.getRole().getRoleName().equals("ADMIN");
    }

    public boolean isOwnedBy(UserEntity user, UrlEntity url) {
        System.out.println("userId: "+user.getUserId());
        System.out.println("createBy: "+url.getCreatedBy().getUserId());
        return url.getCreatedBy().getUserId().equals(user.getUserId());
    }
}
