package com.ecommerce.dataaccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entities.concretes.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
