package br.com.sinuqueiros.restaurant.controllers.order.converters;

import br.com.sinuqueiros.restaurant.controllers.order.requests.ItemRequest;
import br.com.sinuqueiros.restaurant.controllers.order.requests.OrderRequest;
import br.com.sinuqueiros.restaurant.controllers.order.responses.OrderResponse;
import br.com.sinuqueiros.restaurant.services.item.dto.ItemDTO;
import br.com.sinuqueiros.restaurant.services.order.dto.OrderDTO;
import br.com.sinuqueiros.restaurant.services.product.dto.ProductDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderControllerConverter {

    public static OrderDTO convertFromOrderRequestToOrderDTO(final OrderRequest orderRequest) {
        return OrderDTO.builder().tableNumber(orderRequest.tableNumber()).items(convertFromItemRequestListToItemDTOList(orderRequest.items())).build();
    }

    public static ItemDTO convertFromItemRequestToItemDTO(final ItemRequest itemRequest) {
        return ItemDTO.builder().quantity(itemRequest.quantity()).product(ProductDTO.builder().id(itemRequest.productId()).build()).build();
    }

    public static List<ItemDTO> convertFromItemRequestListToItemDTOList(final List<ItemRequest> itemRequestList) {
        return itemRequestList.stream().map(OrderControllerConverter::convertFromItemRequestToItemDTO).toList();
    }

    public static OrderResponse convertFromOrderDTOToOrderResponse(final OrderDTO orderDTO) {
        return new OrderResponse(orderDTO.getId());
    }

    public static List<OrderResponse> convertFromOrderDTOListToOrderResponseList(final List<OrderDTO> orderDTO) {
        return orderDTO.stream().map(OrderControllerConverter::convertFromOrderDTOToOrderResponse).toList();
    }
}
