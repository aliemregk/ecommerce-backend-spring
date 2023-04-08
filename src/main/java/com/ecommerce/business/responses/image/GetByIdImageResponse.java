package com.ecommerce.business.responses.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdImageResponse {
    private int id;
    private String name;
    private String url;
    private int productId;
}
