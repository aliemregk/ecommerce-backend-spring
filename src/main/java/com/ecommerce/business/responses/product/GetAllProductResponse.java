package com.ecommerce.business.responses.product;

import com.ecommerce.entities.concretes.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProductResponse {
    private int id;
    private String name;
    private String description;
    private int stock;
    private double unitPrice;
    private int discount;
    private Category category;
}
