package br.com.sinuqueiros.restaurant.services.order.providers;

import br.com.sinuqueiros.restaurant.repositories.OrderRepository;
import br.com.sinuqueiros.restaurant.services.order.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.sinuqueiros.restaurant.services.order.converters.OrderServiceConverter.convertOrderEntityListToOrderDTOList;

@Component
@RequiredArgsConstructor
public class OrderRepositoryProvider {
    private final OrderRepository orderRepository;

    public List<OrderDTO> findAll() {
        final var orderEntityList = orderRepository.findAll();
        return convertOrderEntityListToOrderDTOList(orderEntityList);
    }
}
