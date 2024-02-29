package br.com.sinuqueiros.restaurant.services.order.converters;

import br.com.sinuqueiros.restaurant.entities.OrderEntity;
import br.com.sinuqueiros.restaurant.services.item.converters.ItemServiceConverter;
import br.com.sinuqueiros.restaurant.services.order.dto.OrderDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderServiceConverter {

    public static OrderDTO convertOrderEntityToOrderDTO(final OrderEntity orderEntity) {
        return OrderDTO.builder()
                .id(orderEntity.getId())
                .tableNumber(orderEntity.getTableNumber())
                .total(orderEntity.getTotal())
                .status(orderEntity.getStatus())
                .items(orderEntity.getItems().stream().map(ItemServiceConverter::convertItemEntityToItemDTO).toList())
                .createdAt(orderEntity.getCreatedAt())
                .updatedAt(orderEntity.getUpdatedAt())
                .build();
    }

    public static OrderEntity convertOrderDTOToOrderEntity(final OrderDTO orderDTO) {
        return OrderEntity.builder()
                .id(orderDTO.getId())
                .items(orderDTO.getItems().stream().map(ItemServiceConverter::convertItemDTOToItemEntity).toList())
                .tableNumber(orderDTO.getTableNumber())
                .total(orderDTO.getTotal())
                .status(orderDTO.getStatus())
                .createdAt(orderDTO.getCreatedAt())
                .updatedAt(orderDTO.getUpdatedAt())
                .build();
    }

    public static List<OrderEntity> convertOrderDTOListToOrderEntityList(final List<OrderDTO> orderDTOList) {
        return orderDTOList.stream().map(OrderServiceConverter::convertOrderDTOToOrderEntity).toList();
    }

    public static List<OrderDTO> convertOrderEntityListToOrderDTOList(final List<OrderEntity> orderEntityList) {
        return orderEntityList.stream().map(OrderServiceConverter::convertOrderEntityToOrderDTO).toList();
    }
}
