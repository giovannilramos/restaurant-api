package br.com.sinuqueiros.restaurant.controllers.order.responses;

import java.math.BigDecimal;
import java.util.List;

public record CloseOrderResponse(Integer tableNumber, List<ItemResponse> itemResponseList, BigDecimal total) {
}
