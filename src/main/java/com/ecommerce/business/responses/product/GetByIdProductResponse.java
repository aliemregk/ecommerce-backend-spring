package com.ecommerce.business.responses.product;

import com.ecommerce.business.responses.category.ProductCategoryResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdProductResponse {
    private String name;
    private String description;
    private int stock;
    private double unitPrice;
    private int discount;
    private ProductCategoryResponse category;
}
