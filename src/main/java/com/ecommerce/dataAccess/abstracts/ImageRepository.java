package com.ecommerce.dataaccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entities.concretes.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    boolean existsByUrl(String url);
}
