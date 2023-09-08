package br.com.sinuqueiros.restaurant.controllers.order;

import br.com.sinuqueiros.restaurant.controllers.order.requests.OrderRequest;
import br.com.sinuqueiros.restaurant.controllers.order.requests.UpdateOrderRequest;
import br.com.sinuqueiros.restaurant.controllers.order.responses.OrderResponse;
import br.com.sinuqueiros.restaurant.services.order.CancelOrderService;
import br.com.sinuqueiros.restaurant.services.order.GetOrderByIdService;
import br.com.sinuqueiros.restaurant.services.order.ListOrderService;
import br.com.sinuqueiros.restaurant.services.order.RequestOrderService;
import br.com.sinuqueiros.restaurant.services.order.UpdateOrderItemsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static br.com.sinuqueiros.restaurant.controllers.order.converters.OrderControllerConverter.convertFromOrderDTOListToOrderResponseList;
import static br.com.sinuqueiros.restaurant.controllers.order.converters.OrderControllerConverter.convertFromOrderDTOToOrderResponse;
import static br.com.sinuqueiros.restaurant.controllers.order.converters.OrderControllerConverter.convertFromOrderRequestToOrderDTO;
import static br.com.sinuqueiros.restaurant.controllers.order.converters.OrderControllerConverter.convertFromUpdateOrderRequestToOrderDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/order")
public class OrderController {
    private final ListOrderService listOrderService;
    private final GetOrderByIdService getOrderByIdService;
    private final RequestOrderService requestOrderService;
    private final CancelOrderService cancelOrderService;
    private final UpdateOrderItemsService updateOrderItemsService;

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody @Valid final OrderRequest orderRequest) {
        requestOrderService.requestOrder(convertFromOrderRequestToOrderDTO(orderRequest));
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> listOrder() {
        final var orderDTOList = listOrderService.listOrder();
        return ResponseEntity.ok(convertFromOrderDTOListToOrderResponseList(orderDTOList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable(name = "id") final Long id) {
        final var orderDTO = getOrderByIdService.getById(id);
        return ResponseEntity.ok(convertFromOrderDTOToOrderResponse(orderDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrderItems(@PathVariable(name = "id") final Long id, @RequestBody final UpdateOrderRequest updateOrderRequest) {
        final var orderDTO = updateOrderItemsService.update(id, convertFromUpdateOrderRequestToOrderDTO(updateOrderRequest));
        return ResponseEntity.ok(convertFromOrderDTOToOrderResponse(orderDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelOrderById(@PathVariable(name = "id") final Long id) {
        cancelOrderService.cancelOrder(id);
        return ResponseEntity.noContent().build();
    }
}
