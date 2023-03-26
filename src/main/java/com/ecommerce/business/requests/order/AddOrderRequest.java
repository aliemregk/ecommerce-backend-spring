package com.ecommerce.business.requests.order;

import java.util.Date;
import java.util.HashMap;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.ecommerce.business.constants.Messages;
import com.ecommerce.business.requests.user.OrderUserModel;
import com.ecommerce.entities.enums.OrderStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddOrderRequest {
    @NotNull(message = "Order date" + Messages.REQUIRED)
    private Date orderDate;

    @NotNull(message = "Order cost" + Messages.REQUIRED)
    @Min(value = 1, message = "Order cost" + Messages.MIN + "1")
    private double totalPrice;

    @NotNull(message = "User info" + Messages.REQUIRED)
    private OrderUserModel user;

    @NotNull(message = "Status" + Messages.REQUIRED)
    private OrderStatusEnum status;

    private Date deliveryDate;

    @NotNull(message = "Order detail" + Messages.REQUIRED)
    private HashMap<Integer, Integer> orderProducts;
}
