package br.com.sinuqueiros.restaurant.services.order;

import br.com.sinuqueiros.restaurant.enums.OrderStatusEnum;
import br.com.sinuqueiros.restaurant.exceptions.NotFoundException;
import br.com.sinuqueiros.restaurant.services.order.dto.OrderDTO;
import br.com.sinuqueiros.restaurant.services.order.providers.OrderRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static br.com.sinuqueiros.restaurant.config.cache.OrderListCache.getOrderResponseList;
import static br.com.sinuqueiros.restaurant.config.cache.OrderListCache.removeOrderResponseItem;

@Service
@RequiredArgsConstructor
public class CancelOrderService implements SendToWebsocket {
    private final OrderRepositoryProvider orderRepositoryProvider;
    private final SimpMessagingTemplate messagingTemplate;

    public void cancelOrder(final Long id) {
        orderRepositoryProvider.findById(id).get();
        orderRepositoryProvider.findById(id).ifPresentOrElse(orderDTO -> {
            orderDTO.setStatus(OrderStatusEnum.CANCELLED);
            orderRepositoryProvider.save(orderDTO);
            sendUpdatedListToWebsocket(orderDTO);
        }, () -> {
            throw new NotFoundException("Order not found!");
        });
    }

    @Override
    public void sendUpdatedListToWebsocket(final OrderDTO orderDTO) {
        removeOrderResponseItem(orderDTO);
        messagingTemplate.convertAndSend("/topic/order", getOrderResponseList());
    }
}
