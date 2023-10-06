package br.com.sinuqueiros.restaurant.controllers.product.requests;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest(@NotNull String name, @NotNull BigDecimal price, @NotNull String description, @NotNull String image) {
}
