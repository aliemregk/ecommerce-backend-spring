package com.ecommerce.business.responses.orderdetail;

import com.ecommerce.business.responses.product.OrderDetailProductModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllByOrderIdOrderDetailResponse {
    private int id;
    private OrderDetailProductModel product;
    private int quantity;
}
