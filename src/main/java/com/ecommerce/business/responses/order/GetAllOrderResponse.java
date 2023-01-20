package com.ecommerce.business.responses.order;

import java.util.Date;

import com.ecommerce.business.responses.user.GetByIdUserResponse;
import com.ecommerce.core.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllOrderResponse {
    private int id;
    private Date orderDate;
    private boolean isDelivered;
    private Date deliveryDate;
    private double totalPrice;
    private GetByIdUserResponse user;
}
