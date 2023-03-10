package com.ecommerce.business.responses.orderdetail;

import com.ecommerce.business.responses.product.OrderDetailProductResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllByOrderIdOrderDetailResponse {
    private int id;
    private OrderDetailProductResponse product;
    private int quantity;
}
