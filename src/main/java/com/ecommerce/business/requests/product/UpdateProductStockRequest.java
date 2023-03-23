package com.ecommerce.business.requests.product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.ecommerce.business.constants.Messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductStockRequest {
    @NotNull(message = "ID" + Messages.REQUIRED)
    private int productId;

    @NotNull(message = "Product quantity" + Messages.REQUIRED)
    @Min(value = 1, message = "Product quantity" + Messages.MIN + "1")
    private int amount;
}
