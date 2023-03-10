package com.ecommerce.business.concretes;

import java.util.List;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecommerce.business.abstracts.ProductService;
import com.ecommerce.business.constants.Messages;
import com.ecommerce.business.requests.product.AddProductRequest;
import com.ecommerce.business.requests.product.DeleteProductRequest;
import com.ecommerce.business.requests.product.UpdateProductRequest;
import com.ecommerce.business.responses.product.GetAllByCategoryIdProductResponse;
import com.ecommerce.business.responses.product.GetAllProductResponse;
import com.ecommerce.business.responses.product.GetByIdProductResponse;
import com.ecommerce.business.rules.ProductBusinessRules;
import com.ecommerce.core.exceptions.BusinessException;
import com.ecommerce.core.utilities.mapper.MapperUtil;
import com.ecommerce.core.utilities.results.Result;
import com.ecommerce.core.utilities.results.SuccessResult;
import com.ecommerce.core.utilities.results.dataresults.DataResult;
import com.ecommerce.core.utilities.results.dataresults.SuccessDataResult;
import com.ecommerce.dataaccess.abstracts.ProductRepository;
import com.ecommerce.entities.concretes.Product;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {

    private static final String MESSAGE = "Product";
    private final ProductRepository productRepository;
    private final ProductBusinessRules productBusinessRules;

    @Cacheable(value = "products")
    @Override
    public DataResult<List<GetAllProductResponse>> getAll() {
        return new SuccessDataResult<>(Messages.LISTED,
                MapperUtil.mapAll(productRepository.findAll(Sort.by(Sort.Direction.ASC, "id")),
                        GetAllProductResponse.class));
    }

    @Override
    public DataResult<GetByIdProductResponse> getById(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BusinessException("No product found with given ID."));
        return new SuccessDataResult<>(Messages.LISTED,
                MapperUtil.map(product, GetByIdProductResponse.class));
    }

    @Cacheable(value = "products")
    @Override
    public DataResult<List<GetAllByCategoryIdProductResponse>> getAllByCategoryId(int id) {
        productBusinessRules.checkIfProductCategoryExists(id);
        return new SuccessDataResult<>(Messages.LISTED,
                MapperUtil.mapAll(productRepository.getAllByCategoryId(id), GetAllByCategoryIdProductResponse.class));
    }

    @CacheEvict(value = "products", allEntries = true)
    @Override
    public Result add(AddProductRequest addProductRequest) {
        productBusinessRules.checkIfProductNameExists(addProductRequest.getName());
        productRepository.save(MapperUtil.map(addProductRequest, Product.class));
        return new SuccessResult(MESSAGE + Messages.ADDED);
    }

    @CacheEvict(value = "products", allEntries = true)
    @Override
    public Result update(UpdateProductRequest updateProductRequest) {
        productBusinessRules.checkIfProductCategoryExists(updateProductRequest.getCategory().getId());
        productBusinessRules.checkIfProductNameChanged(updateProductRequest.getId(), updateProductRequest.getName());
        productRepository.save(MapperUtil.map(updateProductRequest, Product.class));
        return new SuccessResult(MESSAGE + Messages.UPDATED);
    }

    @CacheEvict(value = "products", allEntries = true)
    @Override
    public Result delete(DeleteProductRequest deleteProductRequest) {
        productBusinessRules.checkIfProductExists(deleteProductRequest.getId());
        productRepository.deleteById(deleteProductRequest.getId());
        return new SuccessResult(MESSAGE + Messages.DELETED);
    }

}
