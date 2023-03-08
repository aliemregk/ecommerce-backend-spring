package com.ecommerce.api.controllers;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.business.abstracts.CategoryService;
import com.ecommerce.business.requests.category.AddCategoryRequest;
import com.ecommerce.business.requests.category.DeleteCategoryRequest;
import com.ecommerce.business.requests.category.UpdateCategoryRequest;
import com.ecommerce.business.responses.category.GetAllCategoryResponse;
import com.ecommerce.business.responses.category.GetByIdCategoryResponse;
import com.ecommerce.core.utilities.results.Result;
import com.ecommerce.core.utilities.results.dataresults.DataResult;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin
@AllArgsConstructor
public class CategoriesController {

    private final CategoryService categoryService;

    @GetMapping(path = "/getall")
    public DataResult<List<GetAllCategoryResponse>> getAll() {
        return categoryService.getAll();
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping(path = "/getbyid")
    public DataResult<GetByIdCategoryResponse> getById(@RequestParam int id) {
        return categoryService.getById(id);
    }

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping(path = "/add")
    public Result add(@RequestBody @Valid AddCategoryRequest addCategoryRequest) {
        return categoryService.add(addCategoryRequest);
    }

    @RolesAllowed("ROLE_ADMIN")
    @PutMapping(path = "/update")
    public Result update(@RequestBody @Valid UpdateCategoryRequest updateCategoryRequest) {
        return categoryService.update(updateCategoryRequest);
    }

    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping(path = "/delete")
    public Result delete(@RequestBody @Valid DeleteCategoryRequest deleteCategoryRequest) {
        return categoryService.delete(deleteCategoryRequest);
    }
}
