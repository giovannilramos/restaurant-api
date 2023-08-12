package br.com.sinuqueiros.restaurant.controllers.order;

import br.com.sinuqueiros.restaurant.controllers.order.responses.OrderResponse;
import br.com.sinuqueiros.restaurant.services.order.CreateOrderService;
import br.com.sinuqueiros.restaurant.services.order.ListOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static br.com.sinuqueiros.restaurant.controllers.order.converters.OrderControllerConverter.convertFromOrderDTOListToOrderResponseList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/orders")
public class OrderController {
    private final CreateOrderService createOrderService;
    private final ListOrderService listOrderService;

    @PostMapping
    public ResponseEntity<Void> createOrder() {
        createOrderService.createOrder();
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> listOrder() {
        final var orderDTOList = listOrderService.listOrder();
        return ResponseEntity.ok(convertFromOrderDTOListToOrderResponseList(orderDTOList));
    }
}
