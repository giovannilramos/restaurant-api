package br.com.sinuqueiros.restaurant.controllers.order.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderRequest(@NotNull @NotEmpty List<@Valid ItemRequest> items, @NotNull Integer tableNumber) {
}
