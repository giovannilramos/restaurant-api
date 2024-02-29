package br.com.sinuqueiros.restaurant.services.order.providers;

import br.com.sinuqueiros.restaurant.repositories.OrderRepository;
import br.com.sinuqueiros.restaurant.services.order.converters.OrderServiceConverter;
import br.com.sinuqueiros.restaurant.services.order.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static br.com.sinuqueiros.restaurant.services.order.converters.OrderServiceConverter.convertOrderDTOListToOrderEntityList;
import static br.com.sinuqueiros.restaurant.services.order.converters.OrderServiceConverter.convertOrderDTOToOrderEntity;
import static br.com.sinuqueiros.restaurant.services.order.converters.OrderServiceConverter.convertOrderEntityListToOrderDTOList;
import static br.com.sinuqueiros.restaurant.services.order.converters.OrderServiceConverter.convertOrderEntityToOrderDTO;

@Component
@RequiredArgsConstructor
public class OrderRepositoryProvider {
    private final OrderRepository orderRepository;

    public List<OrderDTO> findAll() {
        final var orderEntityList = orderRepository.findAll();
        return convertOrderEntityListToOrderDTOList(orderEntityList);
    }

    public OrderDTO save(final OrderDTO orderDTO) {
        final var orderEntity = convertOrderDTOToOrderEntity(orderDTO);
        final var saved = orderRepository.save(orderEntity);
        return convertOrderEntityToOrderDTO(saved);
    }

    public Optional<OrderDTO> findById(final Long id) {
        return orderRepository.findById(id).map(OrderServiceConverter::convertOrderEntityToOrderDTO);
    }

    public List<OrderDTO> findAllByTableNumber(final Integer tableNumber) {
        final var orderEntityList =  orderRepository.findAllByTableNumber(tableNumber);
        return convertOrderEntityListToOrderDTOList(orderEntityList);
    }

    public void deleteAll(final List<OrderDTO> orderDTO) {
        final var orderEntityList = convertOrderDTOListToOrderEntityList(orderDTO);
        orderRepository.deleteAll(orderEntityList);
    }

    public void updateStatus(final OrderDTO orderDTO) {
        orderRepository.updateStatus(orderDTO.getId(), orderDTO.getStatus());
    }
}
