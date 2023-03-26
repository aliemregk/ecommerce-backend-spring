package com.ecommerce.business.requests.user;

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
public class UpdateUserRequest {
    @NotNull(message = "User ID" + Messages.REQUIRED)
    private int id;

    @NotNull(message = "First name" + Messages.REQUIRED)
    @NotBlank(message = "First name" + Messages.REQUIRED)
    @Length(min = 2, message = "First name" + Messages.TOO_SHORT)
    private String firstName;

    @NotNull(message = "Last name" + Messages.REQUIRED)
    @NotBlank(message = "Last name" + Messages.REQUIRED)
    @Length(min = 2, message = "Last name" + Messages.TOO_SHORT)
    private String lastName;

    @NotNull(message = "Address" + Messages.REQUIRED)
    @NotBlank(message = "Address" + Messages.REQUIRED)
    @Length(min = 10, message = "Address" + Messages.TOO_SHORT)
    private String address;

    @NotNull(message = "Phone number" + Messages.REQUIRED)
    @NotBlank(message = "Phone number" + Messages.REQUIRED)
    @Length(min = 5, message = "Phone number" + Messages.TOO_SHORT)
    private String phone;

    @NotNull(message = "Email" + Messages.REQUIRED)
    @NotBlank(message = "Email" + Messages.REQUIRED)
    @Email(message = "Email." + Messages.INVALID)
    private String email;

    @NotNull(message = "Password" + Messages.REQUIRED)
    @NotBlank(message = "Password" + Messages.REQUIRED)
    @Length(min = 6, message = "Password" + Messages.TOO_SHORT)
    private String password;
}
