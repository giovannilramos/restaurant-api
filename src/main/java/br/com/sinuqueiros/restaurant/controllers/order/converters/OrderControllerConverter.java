package br.com.sinuqueiros.restaurant.controllers.order.converters;

import br.com.sinuqueiros.restaurant.controllers.order.requests.ItemRequest;
import br.com.sinuqueiros.restaurant.controllers.order.requests.OrderRequest;
import br.com.sinuqueiros.restaurant.controllers.order.requests.UpdateOrderRequest;
import br.com.sinuqueiros.restaurant.controllers.order.responses.ItemResponse;
import br.com.sinuqueiros.restaurant.controllers.order.responses.OrderResponse;
import br.com.sinuqueiros.restaurant.services.item.dto.ItemDTO;
import br.com.sinuqueiros.restaurant.services.order.dto.OrderDTO;
import br.com.sinuqueiros.restaurant.services.product.dto.ProductDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

import static br.com.sinuqueiros.restaurant.controllers.product.converters.ProductControllerConverter.convertFromProductDTOToProductResponse;

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

    public static ItemResponse convertFromItemDTOToItemResponse(final ItemDTO itemDTO) {
        return new ItemResponse(itemDTO.getId(), itemDTO.getQuantity(), itemDTO.getSubTotal(), convertFromProductDTOToProductResponse(itemDTO.getProduct()));
    }

    public static List<ItemResponse> convertFromItemDTOListToItemResponseList(final List<ItemDTO> itemDTOList) {
        return itemDTOList.stream().map(OrderControllerConverter::convertFromItemDTOToItemResponse).toList();
    }

    public static OrderResponse convertFromOrderDTOToOrderResponse(final OrderDTO orderDTO) {
        return new OrderResponse(orderDTO.getId(), orderDTO.getTableNumber(), orderDTO.getTotal(), convertFromItemDTOListToItemResponseList(orderDTO.getItems()), orderDTO.getStatus(), orderDTO.getCreatedAt(), orderDTO.getUpdatedAt());
    }

    public static List<OrderResponse> convertFromOrderDTOListToOrderResponseList(final List<OrderDTO> orderDTO) {
        return orderDTO.stream().map(OrderControllerConverter::convertFromOrderDTOToOrderResponse).toList();
    }

    public static OrderDTO convertFromUpdateOrderRequestToOrderDTO(final UpdateOrderRequest updateOrderRequest) {
        return OrderDTO.builder()
                .items(convertFromItemRequestListToItemDTOList(updateOrderRequest.items()))
                .build();
    }
}
