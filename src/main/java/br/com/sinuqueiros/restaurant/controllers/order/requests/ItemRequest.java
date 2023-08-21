package br.com.sinuqueiros.restaurant.controllers.order.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ItemRequest(@NotNull Integer quantity, @NotNull @Min(1) Long productId) {
}
