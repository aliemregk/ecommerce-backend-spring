package com.ecommerce.business.rules;

import org.springframework.stereotype.Service;

import com.ecommerce.core.exceptions.BusinessException;
import com.ecommerce.dataaccess.abstracts.CategoryRepository;
import com.ecommerce.dataaccess.abstracts.ProductRepository;
import com.ecommerce.entities.concretes.Product;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductBusinessRules {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public void checkIfProductExists(int productId) {
        if (productRepository.findById(productId).isEmpty()) {
            throw new BusinessException("No product found with given ID.");
        }
    }

    public void checkIfProductNameExists(String productName) {
        if (productRepository.existsByName(productName)) {
            throw new BusinessException("Product already exists.");
        }
    }

    public void checkIfProductNameChanged(int productId, String productName) {
        Product result = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException("No product found with given ID."));
        if (!result.getName().equals(productName)) {
            checkIfProductNameExists(productName);
        }
    }

    public void checkIfProductCategoryExists(int categoryId) {
        if (categoryRepository.findById(categoryId).isEmpty()) {
            throw new BusinessException("No category found with given ID.");
        }
    }
}
