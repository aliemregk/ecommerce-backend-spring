package com.ecommerce.business.responses.order;

import java.util.Date;

import com.ecommerce.business.responses.user.OrderUserResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailOrderResponse {
    private int id;
    private Date orderDate;
    private boolean isDelivered;
    private Date deliveryDate;
    private double totalPrice;
    private OrderUserResponse user;
}
