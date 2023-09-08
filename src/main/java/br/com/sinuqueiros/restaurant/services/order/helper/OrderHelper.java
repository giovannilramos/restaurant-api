package br.com.sinuqueiros.restaurant.services.order.helper;

import br.com.sinuqueiros.restaurant.services.item.dto.ItemDTO;
import br.com.sinuqueiros.restaurant.services.order.dto.OrderDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderHelper {

    public static BigDecimal calculateTotal(final OrderDTO orderDTO) {
        return orderDTO.getItems().stream().map(ItemDTO::getSubTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
