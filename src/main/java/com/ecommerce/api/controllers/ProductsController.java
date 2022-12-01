package com.ecommerce.api.controllers;

import java.util.List;

import javax.validation.Valid;

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
import com.ecommerce.entities.concretes.Product;

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
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping(path = "/getbyid")
    public Product getById(@RequestParam int id) {
        return productService.getById(id);
    }

    @PostMapping(path = "/add")
    public void add(@RequestBody @Valid Product product) {
        productService.add(product);
    }

    @PutMapping(path = "/update")
    public void update(@RequestBody @Valid Product product) {
        productService.update(product);
    }

    @DeleteMapping(path = "/delete")
    public void delete(@RequestParam int id) {
        productService.delete(id);
    }

}
