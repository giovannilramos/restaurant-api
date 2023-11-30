package br.com.sinuqueiros.restaurant.controllers.user.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserRequest(@NotBlank String username, @NotBlank String password, @NotNull Integer tableNumber) {
}
