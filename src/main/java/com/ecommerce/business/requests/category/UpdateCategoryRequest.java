package com.ecommerce.business.requests.category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ecommerce.business.constants.Messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryRequest {
    @NotNull(message = "Category ID" + Messages.REQUIRED)
    private int id;

    @NotNull(message = "Category name" + Messages.REQUIRED)
    @NotBlank(message = "Category name" + Messages.REQUIRED)
    @Size(min = 2, message = "Category name" + Messages.TOO_SHORT)
    @Size(max = 15, message = "Category name" + Messages.TOO_LONG)
    private String name;

    @NotNull(message = "Category image" + Messages.REQUIRED)
    @NotBlank(message = "Category image" + Messages.REQUIRED)
    private String imageUrl;
}
