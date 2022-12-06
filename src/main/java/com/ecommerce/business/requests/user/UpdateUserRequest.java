package com.ecommerce.business.requests.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    @NotNull(message = "ID is required.")
    private int id;

    @NotNull(message = "First name is required.")
    @NotBlank(message = "First name is required.")
    @Length(min = 2, message = "First name is too short.")
    private String firstName;

    @NotNull(message = "Last name is required.")
    @NotBlank(message = "Last name is required.")
    @Length(min = 2, message = "Last name is too short.")
    private String lastName;

    @NotNull(message = "Address is required.")
    @NotBlank(message = "Address is required.")
    @Length(min = 10, message = "Address is too short.")
    private String address;

    @NotNull(message = "Phone number is required.")
    @NotBlank(message = "Phone number is required.")
    @Length(min = 5, message = "Phone number is too short.")
    private String phone;

    @NotNull(message = "Email is required.")
    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email.")
    private String email;

    @NotNull(message = "Password is required.")
    @NotBlank(message = "Password is required.")
    @Length(min = 6, message = "Password is too short.")
    private String password;

}
