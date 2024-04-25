package br.com.sinuqueiros.restaurant.controllers.user.responses;

public record LoginResponse(String token, Long expiresIn) {
}
