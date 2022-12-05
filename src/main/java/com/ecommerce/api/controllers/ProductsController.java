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
import com.ecommerce.core.utilities.results.ResultChecker;
import com.ecommerce.core.utilities.results.dataresults.DataResult;

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
        return ResultChecker.checkResult(productService.getAll());
    }

    @GetMapping(path = "/getbyid")
    public ResponseEntity<DataResult<GetByIdProductResponse>> getById(@RequestParam int id) {
        return ResultChecker.checkResult(productService.getProductById(id));
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Result> add(@RequestBody @Valid AddProductRequest addProductRequest) {
        return ResultChecker.checkResult(productService.add(addProductRequest));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Result> update(@RequestBody @Valid UpdateProductRequest updateProductRequest) {
        return ResultChecker.checkResult(productService.update(updateProductRequest));
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<Result> delete(@RequestBody @Valid DeleteProductRequest deleteProductRequest) {
        return ResultChecker.checkResult(productService.delete(deleteProductRequest));
    }

}
