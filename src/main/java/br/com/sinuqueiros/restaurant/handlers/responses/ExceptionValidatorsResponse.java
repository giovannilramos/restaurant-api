package br.com.sinuqueiros.restaurant.handlers.responses;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

public record ExceptionValidatorsResponse(String message, ZonedDateTime zonedDateTime, Map<String, List<String>> errors) {
}
