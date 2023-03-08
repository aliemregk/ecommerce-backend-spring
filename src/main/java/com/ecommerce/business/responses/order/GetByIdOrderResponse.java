package com.ecommerce.business.responses.order;

import java.util.Date;

import com.ecommerce.business.responses.user.GetByIdUserResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdOrderResponse {
    private Date orderDate;
    private boolean isDelivered;
    private Date deliveryDate;
    private double totalPrice;
    private GetByIdUserResponse user;
}
