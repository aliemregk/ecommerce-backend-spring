package com.ecommerce.business.rules;

import org.springframework.stereotype.Service;

import com.ecommerce.core.exceptions.BusinessException;
import com.ecommerce.dataaccess.abstracts.ImageRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ImageBusinessRules {

    private ImageRepository imageRepository;

    public void checkIfImageExists(int imageId) {
        if (imageRepository.findById(imageId).isEmpty()) {
            throw new BusinessException("No image found with given ID.");
        }
    }

    public void checkIfUrlExists(String imageUrl) {
        if (imageRepository.existsByUrl(imageUrl)) {
            throw new BusinessException("Url already exists.");
        }
    }
}
