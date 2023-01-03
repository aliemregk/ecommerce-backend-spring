package com.ecommerce.business.requests.orderdetail;

import javax.validation.constraints.NotNull;

import com.ecommerce.business.constants.Messages;
import com.ecommerce.entities.concretes.Order;
import com.ecommerce.entities.concretes.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderDetailRequest {
    @NotNull(message = "ID" + Messages.REQUIRED)
    private int id;

    @NotNull(message = "Product" + Messages.REQUIRED)
    private Product product;

    @NotNull(message = "Order info" + Messages.REQUIRED)
    private Order order;

    @NotNull(message = "Quantity" + Messages.REQUIRED)
    private int quantity;
}
