package com.ecommerce.business.requests.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.ecommerce.business.constants.Messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotNull(message = "Email" + Messages.REQUIRED)
    @NotBlank(message = "Email" + Messages.REQUIRED)
    @Email(message = "Email" + Messages.INVALID)
    private String email;

    @NotNull(message = "Password" + Messages.REQUIRED)
    @NotBlank(message = "Password" + Messages.REQUIRED)
    @Length(min = 6, message = "Password" + Messages.TOO_SHORT)
    private String password;
}
