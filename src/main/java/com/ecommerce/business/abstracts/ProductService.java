package com.ecommerce.business.abstracts;

import java.util.List;

import com.ecommerce.business.requests.product.AddProductRequest;
import com.ecommerce.business.requests.product.DeleteProductRequest;
import com.ecommerce.business.requests.product.UpdateProductRequest;
import com.ecommerce.business.responses.product.GetAllByCategoryIdProductResponse;
import com.ecommerce.business.responses.product.GetAllProductResponse;
import com.ecommerce.business.responses.product.GetByIdProductResponse;
import com.ecommerce.core.utilities.results.Result;
import com.ecommerce.core.utilities.results.dataresults.DataResult;

public interface ProductService {

    DataResult<List<GetAllProductResponse>> getAll();

    DataResult<GetByIdProductResponse> getById(int id);

    DataResult<List<GetAllByCategoryIdProductResponse>> getAllByCategoryId(int id);

    Result add(AddProductRequest addProductRequest);

    Result update(UpdateProductRequest updateProductRequest);

    Result delete(DeleteProductRequest deleteProductRequest);

    void changeStock(int productId, int amount);
}
