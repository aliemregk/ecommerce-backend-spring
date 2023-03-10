package com.ecommerce.dataaccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entities.concretes.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsByName(String categoryName);
}
