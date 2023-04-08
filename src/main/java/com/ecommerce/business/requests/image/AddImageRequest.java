package com.ecommerce.business.requests.image;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ecommerce.business.constants.Messages;
import com.ecommerce.business.requests.product.ImageProductModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddImageRequest {
    @NotNull(message = "Name" + Messages.REQUIRED)
    @NotBlank(message = "Name" + Messages.REQUIRED)
    @Size(min = 2, message = "Name" + Messages.TOO_SHORT)
    @Size(max = 50, message = "Name" + Messages.TOO_LONG)
    private String name;

    @NotNull(message = "URL" + Messages.REQUIRED)
    @NotBlank(message = "URL" + Messages.REQUIRED)
    private String url;

    @NotNull(message = "Product" + Messages.REQUIRED)
    private ImageProductModel product;
}
