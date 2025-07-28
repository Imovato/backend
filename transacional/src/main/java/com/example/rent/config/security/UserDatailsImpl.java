package com.example.rent.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDatailsImpl implements UserDetails {

    @Serial
    private static final long serialVersionUID = 4735505087197006457L;
    private String userId;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDatailsImpl(String userId, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.authorities = authorities;
    }

    public static UserDatailsImpl build(String userId, String roleStr) {
        List<GrantedAuthority> authorities = Arrays.stream(roleStr.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new UserDatailsImpl(
                userId,
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getUserId() {
        return userId;
    }

}
