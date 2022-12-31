package com.ecommerce.business.abstracts;

import java.util.List;

import com.ecommerce.business.requests.category.AddCategoryRequest;
import com.ecommerce.business.requests.category.DeleteCategoryRequest;
import com.ecommerce.business.requests.category.UpdateCategoryRequest;
import com.ecommerce.business.responses.category.GetAllCategoryResponse;
import com.ecommerce.business.responses.category.GetByIdCategoryResponse;
import com.ecommerce.core.utilities.results.Result;
import com.ecommerce.core.utilities.results.dataresults.DataResult;

public interface CategoryService {

    DataResult<List<GetAllCategoryResponse>> getAll();

    DataResult<GetByIdCategoryResponse> getById(int id);

    Result add(AddCategoryRequest addCategoryRequest);

    Result update(UpdateCategoryRequest updateCategoryRequest);

    Result delete(DeleteCategoryRequest deleteCategoryRequest);
}
