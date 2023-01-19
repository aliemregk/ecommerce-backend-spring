package com.ecommerce.business.responses.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllByCategoryIdProductResponse {
    private int id;
    private String name;
    private String description;
    private int stock;
    private double unitPrice;
    private int discount;
}
