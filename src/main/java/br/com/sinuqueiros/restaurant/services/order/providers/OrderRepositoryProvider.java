package br.com.sinuqueiros.restaurant.services.order.providers;

import br.com.sinuqueiros.restaurant.repositories.OrderRepository;
import br.com.sinuqueiros.restaurant.services.order.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static br.com.sinuqueiros.restaurant.services.order.converters.OrderServiceConverter.convertOrderDTOToOrderEntity;
import static br.com.sinuqueiros.restaurant.services.order.converters.OrderServiceConverter.convertOrderEntityListToOrderDTOList;
import static br.com.sinuqueiros.restaurant.services.order.converters.OrderServiceConverter.convertOrderEntityOptionalToOrderDTOOptional;

@Component
@RequiredArgsConstructor
public class OrderRepositoryProvider {
    private final OrderRepository orderRepository;

    public List<OrderDTO> findAll() {
        final var orderEntityList = orderRepository.findAll();
        return convertOrderEntityListToOrderDTOList(orderEntityList);
    }

    public void save(final OrderDTO orderDTO) {
        final var orderEntity = convertOrderDTOToOrderEntity(orderDTO);
        orderRepository.save(orderEntity);
    }

    public Optional<OrderDTO> findById(final Long id) {
        final var orderEntityOptional = orderRepository.findById(id).orElse(null);
        return convertOrderEntityOptionalToOrderDTOOptional(orderEntityOptional);
    }
}
