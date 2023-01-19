package com.ecommerce.api.controllers;

import java.util.List;

import javax.annotation.security.RolesAllowed;
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
import com.ecommerce.business.requests.product.AddProductRequest;
import com.ecommerce.business.requests.product.DeleteProductRequest;
import com.ecommerce.business.requests.product.UpdateProductRequest;
import com.ecommerce.business.responses.product.GetAllByCategoryIdProductResponse;
import com.ecommerce.business.responses.product.GetAllProductResponse;
import com.ecommerce.business.responses.product.GetByIdProductResponse;
import com.ecommerce.core.utilities.results.Result;
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
    public DataResult<List<GetAllProductResponse>> getAll() {
        return productService.getAll();
    }

    @GetMapping(path = "/getbyid")
    public DataResult<GetByIdProductResponse> getById(@RequestParam int id) {
        return productService.getById(id);
    }

    @GetMapping(path = "/getallbycategoryid")
    public DataResult<List<GetAllByCategoryIdProductResponse>> getAllByCategoryId(@RequestParam int id) {
        return productService.getAllByCategoryId(id);
    }

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping(path = "/add")
    public Result add(@RequestBody @Valid AddProductRequest addProductRequest) {
        return productService.add(addProductRequest);
    }

    @RolesAllowed("ROLE_ADMIN")
    @PutMapping(path = "/update")
    public Result update(@RequestBody @Valid UpdateProductRequest updateProductRequest) {
        return productService.update(updateProductRequest);
    }

    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping(path = "/delete")
    public Result delete(@RequestBody @Valid DeleteProductRequest deleteProductRequest) {
        return productService.delete(deleteProductRequest);
    }

}
