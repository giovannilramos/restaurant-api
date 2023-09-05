package br.com.sinuqueiros.restaurant.controllers.order.responses;

import br.com.sinuqueiros.restaurant.enums.OrderStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(Long id, Integer tableNumber, BigDecimal total, List<ItemResponse> itemResponseList, OrderStatusEnum status, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
