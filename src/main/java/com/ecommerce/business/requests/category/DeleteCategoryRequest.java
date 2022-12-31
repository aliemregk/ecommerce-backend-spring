package com.ecommerce.business.requests.category;

import javax.validation.constraints.NotNull;

import com.ecommerce.business.constants.Messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCategoryRequest {
    @NotNull(message = "Category id " + Messages.REQUIRED)
    private int id;
}
