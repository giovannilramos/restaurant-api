package br.com.sinuqueiros.restaurant.controllers.order.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UpdateOrderRequest(@NotNull @NotEmpty List<@Valid ItemRequest> items) {
}
