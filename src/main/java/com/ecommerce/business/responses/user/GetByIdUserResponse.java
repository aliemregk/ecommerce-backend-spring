package com.ecommerce.business.responses.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdUserResponse {
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
}
