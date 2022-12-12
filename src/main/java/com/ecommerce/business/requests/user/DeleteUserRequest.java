package com.ecommerce.business.requests.user;

import javax.validation.constraints.NotNull;

import com.ecommerce.business.constants.Messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteUserRequest {
    @NotNull(message = "ID" + Messages.REQUIRED)
    private int id;
}
