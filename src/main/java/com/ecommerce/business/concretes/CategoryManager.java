package com.ecommerce.business.concretes;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecommerce.business.abstracts.CategoryService;
import com.ecommerce.business.constants.Messages;
import com.ecommerce.business.requests.category.AddCategoryRequest;
import com.ecommerce.business.requests.category.DeleteCategoryRequest;
import com.ecommerce.business.requests.category.UpdateCategoryRequest;
import com.ecommerce.business.responses.category.GetAllCategoryResponse;
import com.ecommerce.business.responses.category.GetByIdCategoryResponse;
import com.ecommerce.business.rules.CategoryBusinessRules;
import com.ecommerce.core.exceptions.BusinessException;
import com.ecommerce.core.utilities.mapper.MapperUtil;
import com.ecommerce.core.utilities.results.Result;
import com.ecommerce.core.utilities.results.SuccessResult;
import com.ecommerce.core.utilities.results.dataresults.DataResult;
import com.ecommerce.core.utilities.results.dataresults.SuccessDataResult;
import com.ecommerce.dataaccess.abstracts.CategoryRepository;
import com.ecommerce.entities.concretes.Category;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService {

    private static final String MESSAGE = "Category";
    private final CategoryRepository categoryRepository;
    private final CategoryBusinessRules categoryBusinessRules;

    @Override
    public DataResult<List<GetAllCategoryResponse>> getAll() {
        return new SuccessDataResult<>(Messages.LISTED,
                MapperUtil.mapAll(categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "id")),
                        GetAllCategoryResponse.class));
    }

    @Override
    public DataResult<GetByIdCategoryResponse> getById(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new BusinessException("No category found with given ID."));
        return new SuccessDataResult<>(Messages.LISTED,
                MapperUtil.map(category, GetByIdCategoryResponse.class));
    }

    @Override
    public Result add(AddCategoryRequest addCategoryRequest) {
        categoryBusinessRules.checkIfCategoryNameExists(addCategoryRequest.getName());
        categoryRepository.save(MapperUtil.map(addCategoryRequest, Category.class));
        return new SuccessResult(MESSAGE + Messages.ADDED);
    }

    @Override
    public Result update(UpdateCategoryRequest updateCategoryRequest) {
        categoryBusinessRules.checkIfCategoryNameChanged(updateCategoryRequest.getId(),
                updateCategoryRequest.getName());
        categoryRepository.save(MapperUtil.map(updateCategoryRequest, Category.class));
        return new SuccessResult(MESSAGE + Messages.UPDATED);
    }

    @Override
    public Result delete(DeleteCategoryRequest deleteCategoryRequest) {
        categoryBusinessRules.checkIfCategoryExists(deleteCategoryRequest.getId());
        categoryRepository.deleteById(deleteCategoryRequest.getId());
        return new SuccessResult(MESSAGE + Messages.DELETED);
    }

}
