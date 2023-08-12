package br.com.sinuqueiros.restaurant.services.order.converters;

import br.com.sinuqueiros.restaurant.entities.OrderEntity;
import br.com.sinuqueiros.restaurant.services.order.dto.OrderDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderServiceConverter {

    public static OrderDTO convertOrderEntityToOrderDTO(final OrderEntity orderEntity) {
        return OrderDTO.builder().id(orderEntity.getId()).build();
    }

    public static OrderEntity convertOrderDTOToOrderEntity(final OrderDTO orderDTO) {
        return OrderEntity.builder().id(orderDTO.getId()).build();
    }

    public static List<OrderDTO> convertOrderEntityListToOrderDTOList(final List<OrderEntity> orderEntityList) {
        return orderEntityList.stream().map(OrderServiceConverter::convertOrderEntityToOrderDTO).toList();
    }
}
