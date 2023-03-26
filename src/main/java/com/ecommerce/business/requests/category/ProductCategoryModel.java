package com.ecommerce.business.requests.category;

import javax.validation.constraints.NotNull;

import com.ecommerce.business.constants.Messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryModel {
    @NotNull(message = "Category ID" + Messages.REQUIRED)
    private int id;
}
