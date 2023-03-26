package com.ecommerce.business.requests.product;

import javax.validation.constraints.NotNull;

import com.ecommerce.business.constants.Messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailProductModel {
    @NotNull(message = "Product ID" + Messages.REQUIRED)
    private int id;
}
