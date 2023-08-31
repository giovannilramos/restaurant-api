package br.com.sinuqueiros.restaurant.controllers.order.responses;

import br.com.sinuqueiros.restaurant.controllers.product.responses.ProductResponse;

import java.math.BigDecimal;

public record ItemResponse(Long id, Integer quantity, BigDecimal subTotal, ProductResponse productResponse) {
}
