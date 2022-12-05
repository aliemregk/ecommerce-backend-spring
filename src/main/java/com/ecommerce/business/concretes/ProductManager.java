package com.ecommerce.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecommerce.business.abstracts.ProductService;
import com.ecommerce.business.requests.product.AddProductRequest;
import com.ecommerce.business.requests.product.DeleteProductRequest;
import com.ecommerce.business.requests.product.UpdateProductRequest;
import com.ecommerce.business.responses.product.GetAllProductResponse;
import com.ecommerce.business.responses.product.GetByIdProductResponse;
import com.ecommerce.core.utilities.mapper.MapperUtil;
import com.ecommerce.core.utilities.results.ErrorResult;
import com.ecommerce.core.utilities.results.Result;
import com.ecommerce.core.utilities.results.SuccessResult;
import com.ecommerce.core.utilities.results.dataResults.DataResult;
import com.ecommerce.core.utilities.results.dataResults.ErrorDataResult;
import com.ecommerce.core.utilities.results.dataResults.SuccessDataResult;
import com.ecommerce.dataAccess.abstracts.ProductRepository;
import com.ecommerce.entities.concretes.Product;

@Service
public class ProductManager implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Cacheable(value = "products")
    @Override
    public DataResult<List<GetAllProductResponse>> getAll() {
        return new SuccessDataResult<>(
                MapperUtil.mapAll(productRepository.findAll(Sort.by(Sort.Direction.ASC, "id")),
                        GetAllProductResponse.class));
    }

    @Override
    public DataResult<GetByIdProductResponse> getProductById(int id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            return new SuccessDataResult<>(MapperUtil.map(product.get(), GetByIdProductResponse.class));
        }
        return new ErrorDataResult<>("Not found", null);
    }

    @CacheEvict(value = "products", allEntries = true)
    @Override
    public Result add(AddProductRequest addProductRequest) {
        productRepository.save(MapperUtil.map(addProductRequest, Product.class));
        return new SuccessResult("Added.");
    }

    @CacheEvict(value = "products", allEntries = true)
    @Override
    public Result update(UpdateProductRequest updateProductRequest) {
        Optional<Product> productToUpdate = productRepository.findById(updateProductRequest.getId());

        if (productToUpdate.isPresent()) {
            productRepository.save(MapperUtil.map(updateProductRequest, Product.class));
            return new SuccessResult("Updated.");
        }
        return new ErrorResult("Product not found.");
    }

    @CacheEvict(value = "products", allEntries = true)
    @Override
    public Result delete(DeleteProductRequest deleteProductRequest) {
        Optional<Product> productToDelete = productRepository.findById(deleteProductRequest.getId());

        if (productToDelete.isPresent()) {
            productRepository.delete(productToDelete.get());
            return new SuccessResult("Deleted.");
        }
        return new ErrorResult("Product not found.");
    }

}
