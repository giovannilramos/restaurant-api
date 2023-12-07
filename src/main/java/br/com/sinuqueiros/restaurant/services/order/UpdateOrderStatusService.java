package br.com.sinuqueiros.restaurant.services.order;

import br.com.sinuqueiros.restaurant.enums.OrderStatusEnum;
import br.com.sinuqueiros.restaurant.exceptions.NotFoundException;
import br.com.sinuqueiros.restaurant.services.order.dto.OrderDTO;
import br.com.sinuqueiros.restaurant.services.order.providers.OrderRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import static br.com.sinuqueiros.restaurant.config.cache.OrderListCache.getOrderResponseList;
import static br.com.sinuqueiros.restaurant.config.cache.OrderListCache.updateOrderResponseItem;

@Service
@RequiredArgsConstructor
public class UpdateOrderStatusService implements SendToWebsocket {
    private final OrderRepositoryProvider orderRepositoryProvider;
    private final SimpMessagingTemplate messagingTemplate;


    public void updateStatus(final Long id) {
        final var orderDTO = orderRepositoryProvider.findById(id).orElseThrow(() -> new NotFoundException("Order not found"));
        if (orderDTO.getStatus().equals(OrderStatusEnum.REQUESTED)) {
            orderDTO.setStatus(OrderStatusEnum.PREPARING);
            final var orderDTOSaved = orderRepositoryProvider.save(orderDTO);
            sendUpdatedListToWebsocket(orderDTOSaved);
            return;
        }
        if (orderDTO.getStatus().equals(OrderStatusEnum.PREPARING)) {
            orderDTO.setStatus(OrderStatusEnum.FINISHED);
            final var orderDTOSaved = orderRepositoryProvider.save(orderDTO);
            sendUpdatedListToWebsocket(orderDTOSaved);
        }
    }

    @Override
    public void sendUpdatedListToWebsocket(final OrderDTO orderDTO) {
        updateOrderResponseItem(orderDTO);
        messagingTemplate.convertAndSend("/topic/order", getOrderResponseList());
    }
}
