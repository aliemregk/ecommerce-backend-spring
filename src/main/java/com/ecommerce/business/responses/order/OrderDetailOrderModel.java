package com.ecommerce.business.responses.order;

import java.util.Date;

import com.ecommerce.business.responses.user.OrderUserResponse;
import com.ecommerce.entities.enums.OrderStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailOrderModel {
    private int id;
    private Date orderDate;
    private OrderStatusEnum status;
    private Date deliveryDate;
    private double totalPrice;
    private OrderUserResponse user;
}
