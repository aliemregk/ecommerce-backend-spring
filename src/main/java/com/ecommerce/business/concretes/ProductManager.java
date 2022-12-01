package com.ecommerce.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.business.abstracts.ProductService;
import com.ecommerce.dataAccess.abstracts.ProductRepository;
import com.ecommerce.entities.concretes.Product;

@Service
public class ProductManager implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(int id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            return product.get();
        }
        return null;
    }

    @Override
    public void add(Product product) {
        productRepository.save(product);
    }

    @Override
    public void update(Product product) {
        Optional<Product> productToUpdate = productRepository.findById(product.getId());

        if (productToUpdate.isPresent()) {
            productRepository.save(productToUpdate.get());
        }
    }

    @Override
    public void delete(int id) {
        Optional<Product> productToDelete = productRepository.findById(id);

        if (productToDelete.isPresent()) {
            productRepository.delete(productToDelete.get());
        }
    }

}
