package br.com.sinuqueiros.restaurant.mocks;

import br.com.sinuqueiros.restaurant.services.order.dto.OrderDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderMock {
    public static OrderDTO orderDTOMock() {
        return OrderDTO.builder().id(1L).build();
    }
}
