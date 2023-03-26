package com.ecommerce.business.requests.product;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ecommerce.business.constants.Messages;
import com.ecommerce.business.requests.category.ProductCategoryModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProductRequest {
    @NotNull(message = "Name" + Messages.REQUIRED)
    @NotBlank(message = "Name" + Messages.REQUIRED)
    @Size(min = 2, message = "Name" + Messages.TOO_SHORT)
    @Size(max = 50, message = "Name" + Messages.TOO_LONG)
    private String name;

    @NotNull(message = "Description" + Messages.REQUIRED)
    @NotBlank(message = "Description" + Messages.REQUIRED)
    private String description;

    @NotNull(message = "Stock quantity" + Messages.REQUIRED)
    @Min(value = 0, message = "Stock quantity" + Messages.MIN + "0")
    private int stock;

    @NotNull(message = "Unit price" + Messages.REQUIRED)
    @Min(value = 1, message = "Unit price" + Messages.MIN + "1")
    private double unitPrice;

    @Max(value = 99, message = "Discount rate" + Messages.MAX + "99")
    @Min(value = 0, message = "Discount rate" + Messages.MIN + "0")
    private int discount;

    @NotNull(message = "Category " + Messages.REQUIRED)
    private ProductCategoryModel category;
}
