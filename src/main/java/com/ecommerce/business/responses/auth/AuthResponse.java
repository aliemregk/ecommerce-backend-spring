package com.ecommerce.business.responses.auth;

import com.ecommerce.business.responses.user.AuthUserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String token;
    private AuthUserModel user;
}
