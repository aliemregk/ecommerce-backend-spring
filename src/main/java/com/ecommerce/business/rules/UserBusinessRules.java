package com.ecommerce.business.rules;

import org.springframework.stereotype.Service;

import com.ecommerce.core.dataaccess.UserRepository;
import com.ecommerce.core.entities.concretes.User;
import com.ecommerce.core.exceptions.BusinessException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserBusinessRules {

    private final UserRepository userRepository;

    public void checkIfUserExists(int userId) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new BusinessException("No user found with given ID.");
        }
    }

    public void checkIfUserEmailExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new BusinessException("Email is already taken.");
        }
    }

    public void checkIfUserEmailChanged(int userId, String email) {
        User result = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException("No user found with given ID."));
        if (!result.getEmail().equals(email)) {
            checkIfUserEmailExists(email);
        }
    }
}
