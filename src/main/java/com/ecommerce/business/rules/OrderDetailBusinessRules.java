package com.ecommerce.business.rules;

import org.springframework.stereotype.Service;

import com.ecommerce.core.exceptions.BusinessException;
import com.ecommerce.dataaccess.abstracts.OrderDetailRepository;
import com.ecommerce.dataaccess.abstracts.OrderRepository;
import com.ecommerce.dataaccess.abstracts.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderDetailBusinessRules {
    
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public void checkIfOrderDetailExists(int orderDetailId) {
        if(orderDetailRepository.findById(orderDetailId).isEmpty()){
            throw new BusinessException("No order detail found with given ID.");
        }
    }

    public void checkIfOrderExists(int orderId) {
        if(orderRepository.findById(orderId).isEmpty()){
            throw new BusinessException("No order found with given ID.");
        }
    }

    public void checkIfOrderProductExists(int productId) {
        if(productRepository.findById(productId).isEmpty()){
            throw new BusinessException("Product not found.");
        }
    }
}
