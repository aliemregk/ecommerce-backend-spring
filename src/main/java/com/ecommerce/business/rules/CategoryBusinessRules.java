package com.ecommerce.business.rules;

import org.springframework.stereotype.Service;

import com.ecommerce.core.exceptions.BusinessException;
import com.ecommerce.dataaccess.abstracts.CategoryRepository;
import com.ecommerce.entities.concretes.Category;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryBusinessRules {

    private final CategoryRepository categoryRepository;

    public void checkIfCategoryExists(int categoryId) {
        if (categoryRepository.findById(categoryId).isEmpty()) {
            throw new BusinessException("No category found with given ID.");
        }
    }

    public void checkIfCategoryNameExists(String categoryName) {
        if (categoryRepository.existsByName(categoryName)) {
            throw new BusinessException("Category already exists.");
        }
    }

    public void checkIfCategoryNameChanged(int categoryId, String categoryName) {
        Category result = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BusinessException("No category found with given ID."));
        if (!result.getName().equals(categoryName)) {
            checkIfCategoryNameExists(categoryName);
        }
    }
}
