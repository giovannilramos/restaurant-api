package br.com.sinuqueiros.restaurant.controllers.user.requests;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank String username, @NotBlank String password) {
}
