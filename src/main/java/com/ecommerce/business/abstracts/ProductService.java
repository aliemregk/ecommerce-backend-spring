package com.ecommerce.business.abstracts;

import java.util.List;

import com.ecommerce.business.requests.product.AddProductRequest;
import com.ecommerce.business.requests.product.DeleteProductRequest;
import com.ecommerce.business.requests.product.UpdateProductRequest;
import com.ecommerce.business.responses.product.GetAllProductResponse;
import com.ecommerce.business.responses.product.GetByIdProductResponse;
import com.ecommerce.core.utilities.results.Result;
import com.ecommerce.core.utilities.results.dataResults.DataResult;

public interface ProductService {

    DataResult<List<GetAllProductResponse>> getAll();

    DataResult<GetByIdProductResponse> getProductById(int id);

    Result add(AddProductRequest addProductRequest);

    Result update(UpdateProductRequest updateProductRequest);

    Result delete(DeleteProductRequest deleteProductRequest);
}
