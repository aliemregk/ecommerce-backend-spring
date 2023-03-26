package com.ecommerce.business.requests.order;

import javax.validation.constraints.NotNull;

import com.ecommerce.business.constants.Messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailOrderModel {
    @NotNull(message = "Order ID" + Messages.REQUIRED)
    private int id;
}
