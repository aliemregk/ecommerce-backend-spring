package com.ecommerce.business.responses.orderdetail;

import com.ecommerce.business.responses.order.OrderDetailOrderModel;
import com.ecommerce.business.responses.product.OrderDetailProductModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllOrderDetailResponse {
    private int id;
    private OrderDetailProductModel product;
    private OrderDetailOrderModel order;
    private int quantity;
}
