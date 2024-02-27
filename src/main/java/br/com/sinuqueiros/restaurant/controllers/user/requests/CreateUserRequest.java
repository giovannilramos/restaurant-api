package br.com.sinuqueiros.restaurant.controllers.user.requests;

import br.com.sinuqueiros.restaurant.enums.RoleNameEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserRequest(
        @NotBlank String username,
        @NotBlank String password,
        @NotNull Integer tableNumber,
        @NotNull
        RoleNameEnum role
) {
}
