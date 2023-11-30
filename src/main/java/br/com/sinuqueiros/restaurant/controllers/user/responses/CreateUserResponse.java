package br.com.sinuqueiros.restaurant.controllers.user.responses;

import java.time.LocalDateTime;

public record CreateUserResponse(String username, Integer tableNumber, LocalDateTime createdAt) {
}
