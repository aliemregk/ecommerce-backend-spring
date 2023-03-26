package com.ecommerce.business.requests.orderdetail;

import javax.validation.constraints.NotNull;

import com.ecommerce.business.constants.Messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteOrderDetailRequest {
    @NotNull(message = "Order Detail ID" + Messages.REQUIRED)
    private int id;
}
