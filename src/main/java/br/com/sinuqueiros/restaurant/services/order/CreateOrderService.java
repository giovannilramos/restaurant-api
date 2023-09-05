package br.com.sinuqueiros.restaurant.services.order;

import br.com.sinuqueiros.restaurant.services.item.CreateItemService;
import br.com.sinuqueiros.restaurant.services.order.dto.OrderDTO;
import br.com.sinuqueiros.restaurant.services.order.providers.OrderRepositoryProvider;
import br.com.sinuqueiros.restaurant.services.product.providers.ProductRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static br.com.sinuqueiros.restaurant.config.cache.OrderListCache.addOrderResponseItem;
import static br.com.sinuqueiros.restaurant.config.cache.OrderListCache.getOrderResponseList;

@Service
@RequiredArgsConstructor
public class CreateOrderService {
    private final OrderRepositoryProvider orderRepositoryProvider;
    private final CreateItemService createItemService;
    private final ProductRepositoryProvider productRepositoryProvider;
    private final SimpMessagingTemplate messagingTemplate;

    @Transactional
    public void createOrder(final OrderDTO orderDTO) {
        final var total = calculateTotal(orderDTO);
        orderDTO.setTotal(total);
        final var itemDTOList = createItemService.save(orderDTO.getItems());
        orderDTO.setItems(itemDTOList);
        final var orderDTOSaved = orderRepositoryProvider.save(orderDTO);
        sendUpdatedListToWebsocket(orderDTOSaved);
    }

    private void sendUpdatedListToWebsocket(final OrderDTO orderDTO) {
        addOrderResponseItem(orderDTO);
        messagingTemplate.convertAndSend("/topic/order", getOrderResponseList());
    }

    private BigDecimal calculateTotal(final OrderDTO orderDTO) {
        return orderDTO.getItems().stream().map(itemDTO -> {
            final var productDTO = productRepositoryProvider.findById(itemDTO.getProduct().getId()).orElseThrow();
            final var subTotal = productDTO.getPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity()));
            itemDTO.setSubTotal(subTotal);
            return subTotal;
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}



