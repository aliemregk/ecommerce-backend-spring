package com.ecommerce.business.requests.image;

import javax.validation.constraints.NotNull;

import com.ecommerce.business.constants.Messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteImageRequest {
    @NotNull(message = "Image ID" + Messages.REQUIRED)
    private int id;
}
