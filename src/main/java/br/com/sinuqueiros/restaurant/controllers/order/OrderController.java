package br.com.sinuqueiros.restaurant.controllers.order;

import br.com.sinuqueiros.restaurant.controllers.order.requests.OrderRequest;
import br.com.sinuqueiros.restaurant.controllers.order.responses.OrderResponse;
import br.com.sinuqueiros.restaurant.services.order.CreateOrderService;
import br.com.sinuqueiros.restaurant.services.order.GetOrderByIdService;
import br.com.sinuqueiros.restaurant.services.order.ListOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static br.com.sinuqueiros.restaurant.controllers.helpers.UriHelper.getUri;
import static br.com.sinuqueiros.restaurant.controllers.order.converters.OrderControllerConverter.convertFromOrderDTOListToOrderResponseList;
import static br.com.sinuqueiros.restaurant.controllers.order.converters.OrderControllerConverter.convertFromOrderDTOToOrderResponse;
import static br.com.sinuqueiros.restaurant.controllers.order.converters.OrderControllerConverter.convertFromOrderRequestToOrderDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/orders")
public class OrderController {
    private final CreateOrderService createOrderService;
    private final ListOrderService listOrderService;
    private final GetOrderByIdService getOrderByIdService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid final OrderRequest orderRequest, final UriComponentsBuilder uriComponentsBuilder) {
        final var orderDTO = createOrderService.createOrder(convertFromOrderRequestToOrderDTO(orderRequest));
        final var uri = getUri(uriComponentsBuilder, "/v1/orders/{id}", orderDTO.getId());
        final var orderResponse = convertFromOrderDTOToOrderResponse(orderDTO);
        return ResponseEntity.created(uri).body(orderResponse);
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
