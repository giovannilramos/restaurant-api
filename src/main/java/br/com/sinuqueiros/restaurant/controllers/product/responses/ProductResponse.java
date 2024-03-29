package br.com.sinuqueiros.restaurant.controllers.product.responses;

import br.com.sinuqueiros.restaurant.enums.CategoryEnum;

import java.math.BigDecimal;

public record ProductResponse(Long id, String description, String image, String name, BigDecimal price, Boolean status, CategoryEnum category) {
}
