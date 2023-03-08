package com.ecommerce.dataaccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entities.concretes.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> getAllByCategoryId(int id);
}
