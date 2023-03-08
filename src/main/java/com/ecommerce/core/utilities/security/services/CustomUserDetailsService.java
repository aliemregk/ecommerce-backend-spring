package com.ecommerce.core.utilities.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.core.dataaccess.UserRepository;
import com.ecommerce.core.utilities.security.models.SecurityUser;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.getByEmail(email).map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found."));
    }

}
