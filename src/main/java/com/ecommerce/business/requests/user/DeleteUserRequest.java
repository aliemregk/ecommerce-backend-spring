package com.ecommerce.business.requests.user;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteUserRequest {
    @NotNull(message = "ID is required.")
    private int id;
}
