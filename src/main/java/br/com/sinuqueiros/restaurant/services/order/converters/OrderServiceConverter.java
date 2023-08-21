package br.com.sinuqueiros.restaurant.services.order.converters;

import br.com.sinuqueiros.restaurant.entities.OrderEntity;
import br.com.sinuqueiros.restaurant.services.item.converters.ItemServiceConverter;
import br.com.sinuqueiros.restaurant.services.order.dto.OrderDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderServiceConverter {

    public static OrderDTO convertOrderEntityToOrderDTO(final OrderEntity orderEntity) {
        return OrderDTO.builder().id(orderEntity.getId()).build();
    }

    public static OrderEntity convertOrderDTOToOrderEntity(final OrderDTO orderDTO) {
        return OrderEntity.builder()
                .id(orderDTO.getId())
                .items(orderDTO.getItems().stream().map(ItemServiceConverter::convertItemDTOToItemEntity).toList())
                .tableNumber(orderDTO.getTableNumber())
                .total(orderDTO.getTotal())
                .createdAt(orderDTO.getCreatedAt())
                .updatedAt(orderDTO.getUpdatedAt())
                .build();
    }

    public static List<OrderDTO> convertOrderEntityListToOrderDTOList(final List<OrderEntity> orderEntityList) {
        return orderEntityList.stream().map(OrderServiceConverter::convertOrderEntityToOrderDTO).toList();
    }

    public static Optional<OrderDTO> convertOrderEntityOptionalToOrderDTOOptional(final OrderEntity orderEntity) {
        if (isNull(orderEntity)) {
            return Optional.empty();
        }
        return Optional.ofNullable(convertOrderEntityToOrderDTO(orderEntity));
    }
}
