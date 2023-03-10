package com.ecommerce.business.responses.orderdetail;

import com.ecommerce.business.responses.order.OrderDetailOrderResponse;
import com.ecommerce.business.responses.product.OrderDetailProductResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdOrderDetailResponse {
    private OrderDetailProductResponse product;
    private OrderDetailOrderResponse order;
    private int quantity;
}
