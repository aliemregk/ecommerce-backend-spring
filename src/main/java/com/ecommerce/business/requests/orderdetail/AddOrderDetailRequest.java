package com.ecommerce.business.requests.orderdetail;

import javax.validation.constraints.NotNull;

import com.ecommerce.business.constants.Messages;
import com.ecommerce.business.requests.order.OrderDetailOrderModel;
import com.ecommerce.business.requests.product.OrderDetailProductModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddOrderDetailRequest {
    @NotNull(message = "Product" + Messages.REQUIRED)
    private OrderDetailProductModel product;

    @NotNull(message = "Order info" + Messages.REQUIRED)
    private OrderDetailOrderModel order;

    @NotNull(message = "Quantity" + Messages.REQUIRED)
    private int quantity;
}
