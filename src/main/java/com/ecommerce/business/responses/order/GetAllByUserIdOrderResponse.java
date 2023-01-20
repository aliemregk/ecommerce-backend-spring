package com.ecommerce.business.responses.order;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllByUserIdOrderResponse {
    private int id;
    private Date orderDate;
    private boolean isDelivered;
    private Date deliveryDate;
    private double totalPrice;
}
