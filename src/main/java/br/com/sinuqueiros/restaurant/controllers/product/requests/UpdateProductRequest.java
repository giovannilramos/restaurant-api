package br.com.sinuqueiros.restaurant.controllers.product.requests;

import java.math.BigDecimal;

public record UpdateProductRequest(String name, BigDecimal price, String description, String image) {
}
