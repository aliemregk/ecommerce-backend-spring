package com.ecommerce.business.abstracts;

import java.util.List;

import com.ecommerce.entities.concretes.Product;

public interface ProductService {

    List<Product> getAll();

    Product getById(int id);

    void add(Product product);

    void update(Product product);

    void delete(int id);
}
