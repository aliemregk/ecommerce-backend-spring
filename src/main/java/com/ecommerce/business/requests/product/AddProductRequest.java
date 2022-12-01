package com.ecommerce.business.requests.product;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProductRequest {
    @NotNull(message = "Name is required.")
    @NotBlank(message = "Name is required.")
    @Size(min = 2, message = "Name is too short.")
    @Size(max = 50, message = "Name is too long.")
    private String name;

    @NotNull(message = "Description is required.")
    @NotBlank(message = "Description is required.")
    private String description;

    @NotNull(message = "Stock quantity is required.")
    @Min(value = 0, message = "Stock quantity can not be less than zero.")
    private int stock;

    @NotNull(message = "Unit price is required.")
    @Min(value = 1, message = "Unit price must at least 1.")
    private double unitPrice;

    @Max(value = 99, message = "Discount rate must between 0 and 99.")
    @Min(value = 0, message = "Discount rate must between 0 and 99.")
    private int discount;
}
