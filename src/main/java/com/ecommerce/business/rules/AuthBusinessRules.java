package com.ecommerce.business.rules;

import org.springframework.stereotype.Service;

import com.ecommerce.core.dataaccess.UserRepository;
import com.ecommerce.core.exceptions.BusinessException;
import com.ecommerce.core.utilities.security.hashing.HashingUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthBusinessRules {

    private final UserRepository userRepository;

    public void checkIfUserEmailExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new BusinessException("Email is already taken.");
        }
    }

    public void checkPassword(String passwordFromRequest, String passwordFromRecord) {
        if (!HashingUtil.verifyPassword(passwordFromRequest, passwordFromRecord)) {
            throw new BusinessException("Wrong password.");
        }
    }

    public void checkToken(String token) {
        if (token.isEmpty() || token.isBlank()) {
            throw new BusinessException("Error while creating token.");
        }
    }
}
