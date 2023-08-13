package br.com.sinuqueiros.restaurant.handlers.responses;

import java.time.ZonedDateTime;

public record ExceptionResponse (String message, ZonedDateTime zonedDateTime) {
}
