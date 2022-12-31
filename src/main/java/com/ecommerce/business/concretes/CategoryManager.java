package com.ecommerce.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecommerce.business.abstracts.CategoryService;
import com.ecommerce.business.constants.Messages;
import com.ecommerce.business.requests.category.AddCategoryRequest;
import com.ecommerce.business.requests.category.DeleteCategoryRequest;
import com.ecommerce.business.requests.category.UpdateCategoryRequest;
import com.ecommerce.business.responses.category.GetAllCategoryResponse;
import com.ecommerce.business.responses.category.GetByIdCategoryResponse;
import com.ecommerce.core.utilities.mapper.MapperUtil;
import com.ecommerce.core.utilities.results.ErrorResult;
import com.ecommerce.core.utilities.results.Result;
import com.ecommerce.core.utilities.results.SuccessResult;
import com.ecommerce.core.utilities.results.dataresults.DataResult;
import com.ecommerce.core.utilities.results.dataresults.ErrorDataResult;
import com.ecommerce.core.utilities.results.dataresults.SuccessDataResult;
import com.ecommerce.dataaccess.abstracts.CategoryRepository;
import com.ecommerce.entities.concretes.Category;

@Service
public class CategoryManager implements CategoryService {

    private static final String MESSAGE = "Category";
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryManager(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public DataResult<List<GetAllCategoryResponse>> getAll() {
        return new SuccessDataResult<>(Messages.LISTED,
                MapperUtil.mapAll(categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "id")),
                        GetAllCategoryResponse.class));
    }

    @Override
    public DataResult<GetByIdCategoryResponse> getById(int id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            return new SuccessDataResult<>(Messages.LISTED,
                    MapperUtil.map(category.get(), GetByIdCategoryResponse.class));
        }
        return new ErrorDataResult<>(Messages.NOT_FOUND, null);
    }

    @Override
    public Result add(AddCategoryRequest addCategoryRequest) {
        categoryRepository.save(MapperUtil.map(addCategoryRequest, Category.class));
        return new SuccessResult(MESSAGE + Messages.ADDED);
    }

    @Override
    public Result update(UpdateCategoryRequest updateCategoryRequest) {
        Optional<Category> categoryToUpdate = categoryRepository.findById(updateCategoryRequest.getId());
        if (categoryToUpdate.isPresent()) {
            categoryRepository.save(MapperUtil.map(updateCategoryRequest, Category.class));
            return new SuccessResult(MESSAGE + Messages.UPDATED);
        }
        return new ErrorResult(MESSAGE + Messages.NOT_FOUND);
    }

    @Override
    public Result delete(DeleteCategoryRequest deleteCategoryRequest) {
        Optional<Category> categoryToDelete = categoryRepository.findById(deleteCategoryRequest.getId());
        if (categoryToDelete.isPresent()) {
            categoryRepository.delete(MapperUtil.map(deleteCategoryRequest, Category.class));
            return new SuccessResult(MESSAGE + Messages.DELETED);
        }
        return new ErrorResult(MESSAGE + Messages.NOT_FOUND);
    }

}
