package com.ecommerce.business.rules;

import org.springframework.stereotype.Service;

import com.ecommerce.core.dataaccess.UserRepository;
import com.ecommerce.core.exceptions.BusinessException;
import com.ecommerce.dataaccess.abstracts.OrderRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderBusinessRules {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public void checkIfOrderExists(int orderId) {
        if (orderRepository.findById(orderId).isEmpty()) {
            throw new BusinessException("Order not found.");
        }
    }

    public void checkIfUserExists(int userId) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new BusinessException("User not found.");
        }
    }
}
