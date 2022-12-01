package com.ecommerce.business.requests.product;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteProductRequest {
    @NotNull(message = "ID is required.")
    private int id;
}
