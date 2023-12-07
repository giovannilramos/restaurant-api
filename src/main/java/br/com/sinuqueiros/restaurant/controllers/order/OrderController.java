package br.com.sinuqueiros.restaurant.controllers.order;

import br.com.sinuqueiros.restaurant.controllers.order.requests.OrderRequest;
import br.com.sinuqueiros.restaurant.controllers.order.requests.UpdateOrderRequest;
import br.com.sinuqueiros.restaurant.controllers.order.responses.CloseOrderResponse;
import br.com.sinuqueiros.restaurant.controllers.order.responses.OrderResponse;
import br.com.sinuqueiros.restaurant.services.order.CancelOrderService;
import br.com.sinuqueiros.restaurant.services.order.CloseOrderService;
import br.com.sinuqueiros.restaurant.services.order.GetOrderByIdService;
import br.com.sinuqueiros.restaurant.services.order.ListOrderService;
import br.com.sinuqueiros.restaurant.services.order.RequestOrderService;
import br.com.sinuqueiros.restaurant.services.order.UpdateOrderItemsService;
import br.com.sinuqueiros.restaurant.services.order.UpdateOrderStatusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static br.com.sinuqueiros.restaurant.controllers.order.converters.OrderControllerConverter.convertFromOrderDTOListToOrderResponseList;
import static br.com.sinuqueiros.restaurant.controllers.order.converters.OrderControllerConverter.convertFromOrderDTOToCloseOrderResponse;
import static br.com.sinuqueiros.restaurant.controllers.order.converters.OrderControllerConverter.convertFromOrderDTOToOrderResponse;
import static br.com.sinuqueiros.restaurant.controllers.order.converters.OrderControllerConverter.convertFromOrderRequestToOrderDTO;
import static br.com.sinuqueiros.restaurant.controllers.order.converters.OrderControllerConverter.convertFromUpdateOrderRequestToOrderDTO;
import static br.com.sinuqueiros.restaurant.services.user.helper.UserHelper.decoderTokenJwt;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/order")
public class OrderController {
    private final ListOrderService listOrderService;
    private final GetOrderByIdService getOrderByIdService;
    private final RequestOrderService requestOrderService;
    private final CancelOrderService cancelOrderService;
    private final UpdateOrderItemsService updateOrderItemsService;
    private final UpdateOrderStatusService updateOrderStatusService;
    private final CloseOrderService closeOrderService;

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestHeader(name = "Authorization") final String jwtToken, @RequestBody @Valid final OrderRequest orderRequest) {
        final var tableNumber = decoderTokenJwt(jwtToken);
        requestOrderService.requestOrder(convertFromOrderRequestToOrderDTO(tableNumber, orderRequest));
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

    @GetMapping("/{id}/status")
    public ResponseEntity<OrderResponse> updateOrderStatus(@PathVariable(name = "id") final Long id) {
        updateOrderStatusService.updateStatus(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelOrderById(@PathVariable(name = "id") final Long id) {
        cancelOrderService.cancelOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/close-order/{tableNumber}")
    public ResponseEntity<CloseOrderResponse> closeOrder(@PathVariable(name = "tableNumber") final Integer tableNumber) {
        final var orderDTO = closeOrderService.closeOrder(tableNumber);
        return ResponseEntity.ok(convertFromOrderDTOToCloseOrderResponse(orderDTO));
    }
}
