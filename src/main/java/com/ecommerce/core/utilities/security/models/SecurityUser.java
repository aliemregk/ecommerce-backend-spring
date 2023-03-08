package com.ecommerce.core.utilities.security.models;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ecommerce.core.entities.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SecurityUser implements UserDetails {

    private User user;

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (user.isAdmin()) {
            return Arrays.asList("ROLE_ADMIN").stream().map(SimpleGrantedAuthority::new).toList();
        } else {
            return Arrays.asList("ROLE_USER").stream().map(SimpleGrantedAuthority::new).toList();
        }
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

}
