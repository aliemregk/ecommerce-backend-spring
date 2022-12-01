package com.ecommerce.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecommerce.business.abstracts.ProductService;
import com.ecommerce.business.requests.product.AddProductRequest;
import com.ecommerce.business.requests.product.DeleteProductRequest;
import com.ecommerce.business.requests.product.UpdateProductRequest;
import com.ecommerce.business.responses.product.GetAllProductResponse;
import com.ecommerce.business.responses.product.GetByIdProductResponse;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductsController {

    private final ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/getall")
    public List<GetAllProductResponse> getAll() {
        return productService.getAll();
    }

    @GetMapping(path = "/getbyid")
    public GetByIdProductResponse getById(@RequestParam int id) {
        return productService.getById(id);
    }

    @PostMapping(path = "/add")
    public void add(@RequestBody AddProductRequest addProductRequest) {
        productService.add(addProductRequest);
    }

    @PutMapping(path = "/update")
    public void update(@RequestBody UpdateProductRequest updateProductRequest) {
        productService.update(updateProductRequest);
    }

    @DeleteMapping(path = "/delete")
    public void delete(@RequestBody DeleteProductRequest deleteProductRequest) {
        productService.delete(deleteProductRequest);
    }

}
