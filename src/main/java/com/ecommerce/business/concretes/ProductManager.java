package com.ecommerce.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.business.abstracts.ProductService;
import com.ecommerce.business.requests.product.AddProductRequest;
import com.ecommerce.business.requests.product.DeleteProductRequest;
import com.ecommerce.business.requests.product.UpdateProductRequest;
import com.ecommerce.business.responses.product.GetAllProductResponse;
import com.ecommerce.business.responses.product.GetByIdProductResponse;
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
    public List<GetAllProductResponse> getAll() {
        List<Product> products = productRepository.findAll();
        List<GetAllProductResponse> productResponse = new ArrayList<GetAllProductResponse>();

        for (Product product : products) {
            GetAllProductResponse response = new GetAllProductResponse(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getStock(),
                    product.getUnitPrice(),
                    product.getDiscount());

            productResponse.add(response);
        }
        return productResponse;
    }

    @Override
    public GetByIdProductResponse getById(int id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            return new GetByIdProductResponse(
                    product.get().getName(),
                    product.get().getDescription(),
                    product.get().getStock(),
                    product.get().getUnitPrice(),
                    product.get().getDiscount());
        }
        return null;
    }

    @Override
    public void add(AddProductRequest addProductRequest) {
        Product productToAdd = new Product(
                0,
                addProductRequest.getName(),
                addProductRequest.getDescription(),
                addProductRequest.getStock(),
                addProductRequest.getUnitPrice(),
                addProductRequest.getDiscount());
        productRepository.save(productToAdd);
    }

    @Override
    public void update(UpdateProductRequest updateProductRequest) {
        Optional<Product> productToUpdate = productRepository.findById(updateProductRequest.getId());

        if (productToUpdate.isPresent()) {
            Product updatedProduct = new Product(
                    updateProductRequest.getId(),
                    updateProductRequest.getName(),
                    updateProductRequest.getDescription(),
                    updateProductRequest.getStock(),
                    updateProductRequest.getUnitPrice(),
                    updateProductRequest.getDiscount());
            productRepository.save(updatedProduct);
        }
    }

    @Override
    public void delete(DeleteProductRequest deleteProductRequest) {
        Optional<Product> productToDelete = productRepository.findById(deleteProductRequest.getId());

        if (productToDelete.isPresent()) {
            productRepository.delete(productToDelete.get());
        }
    }

}
