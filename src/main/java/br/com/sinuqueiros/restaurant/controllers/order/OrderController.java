package br.com.sinuqueiros.restaurant.controllers.order;

import br.com.sinuqueiros.restaurant.controllers.order.requests.OrderRequest;
import br.com.sinuqueiros.restaurant.controllers.order.responses.OrderResponse;
import br.com.sinuqueiros.restaurant.services.order.GetOrderByIdService;
import br.com.sinuqueiros.restaurant.services.order.ListOrderService;
import br.com.sinuqueiros.restaurant.services.order.RequestOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static br.com.sinuqueiros.restaurant.controllers.order.converters.OrderControllerConverter.convertFromOrderDTOListToOrderResponseList;
import static br.com.sinuqueiros.restaurant.controllers.order.converters.OrderControllerConverter.convertFromOrderDTOToOrderResponse;
import static br.com.sinuqueiros.restaurant.controllers.order.converters.OrderControllerConverter.convertFromOrderRequestToOrderDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/order")
public class OrderController {
    private final ListOrderService listOrderService;
    private final GetOrderByIdService getOrderByIdService;
    private final RequestOrderService requestOrderService;

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
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable(name = "id")  final Long id) {
        final var orderDTO = getOrderByIdService.getById(id);
        return ResponseEntity.ok(convertFromOrderDTOToOrderResponse(orderDTO));
    }
}
