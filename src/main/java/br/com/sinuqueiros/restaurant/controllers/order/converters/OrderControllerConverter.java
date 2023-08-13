package br.com.sinuqueiros.restaurant.controllers.order.converters;

import br.com.sinuqueiros.restaurant.controllers.order.requests.OrderRequest;
import br.com.sinuqueiros.restaurant.controllers.order.responses.OrderResponse;
import br.com.sinuqueiros.restaurant.services.order.dto.OrderDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderControllerConverter {

    public static OrderDTO convertFromOrderRequestToOrderDTO(final OrderRequest orderRequest) {
        return OrderDTO.builder().build();
    }

    public static OrderResponse convertFromOrderDTOToOrderResponse(final OrderDTO orderDTO) {
        return new OrderResponse(orderDTO.getId());
    }

    public static List<OrderResponse> convertFromOrderDTOListToOrderResponseList(final List<OrderDTO> orderDTO) {
        return orderDTO.stream().map(OrderControllerConverter::convertFromOrderDTOToOrderResponse).toList();
    }
}
