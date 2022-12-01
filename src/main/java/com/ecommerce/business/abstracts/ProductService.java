package com.ecommerce.business.abstracts;

import java.util.List;

import com.ecommerce.business.requests.product.AddProductRequest;
import com.ecommerce.business.requests.product.DeleteProductRequest;
import com.ecommerce.business.requests.product.UpdateProductRequest;
import com.ecommerce.business.responses.product.GetAllProductResponse;
import com.ecommerce.business.responses.product.GetByIdProductResponse;

public interface ProductService {

    List<GetAllProductResponse> getAll();

    GetByIdProductResponse getById(int id);

    void add(AddProductRequest addProductRequest);

    void update(UpdateProductRequest updateProductRequest);

    void delete(DeleteProductRequest deleteProductRequest);
}
