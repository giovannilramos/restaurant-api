package br.com.sinuqueiros.restaurant.config.cache;

import br.com.sinuqueiros.restaurant.controllers.order.responses.OrderResponse;
import br.com.sinuqueiros.restaurant.exceptions.NotFoundException;
import br.com.sinuqueiros.restaurant.services.order.dto.OrderDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static br.com.sinuqueiros.restaurant.controllers.order.converters.OrderControllerConverter.convertFromOrderDTOListToOrderResponseList;

@Component
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderListCache {
    protected static final List<OrderDTO> orderDTOList = new ArrayList<>();

    public static void addOrderResponseItem(final OrderDTO orderDTO) {
        orderDTOList.add(orderDTO);
    }

    public static void removeOrderResponseItem(final OrderDTO orderDTO) {
        orderDTOList.remove(orderDTO);
    }

    public static void updateOrderResponseItem(final OrderDTO orderDTO) {
        final var orderDTOFiltered = orderDTOList.stream()
                .filter(orderDTOItem -> orderDTOItem.getId().equals(orderDTO.getId()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Order not found!"));
        orderDTOList.set(orderDTOList.indexOf(orderDTOFiltered), orderDTO);
    }

    public static List<OrderResponse> getOrderResponseList() {
        return convertFromOrderDTOListToOrderResponseList(orderDTOList);
    }
}
