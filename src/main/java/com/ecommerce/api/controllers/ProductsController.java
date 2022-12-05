package com.ecommerce.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.ecommerce.core.utilities.results.Result;
import com.ecommerce.core.utilities.results.dataResults.DataResult;

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
    public ResponseEntity<DataResult<List<GetAllProductResponse>>> getAll() {
        DataResult<List<GetAllProductResponse>> result = productService.getAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/getbyid")
    public ResponseEntity<DataResult<GetByIdProductResponse>> getById(@RequestParam int id) {
        DataResult<GetByIdProductResponse> result = productService.getProductById(id);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Result> add(@RequestBody @Valid AddProductRequest addProductRequest) {
        Result result = productService.add(addProductRequest);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Result> update(@RequestBody @Valid UpdateProductRequest updateProductRequest) {
        Result result = productService.update(updateProductRequest);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<Result> delete(@RequestBody @Valid DeleteProductRequest deleteProductRequest) {
        Result result = productService.delete(deleteProductRequest);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

}
