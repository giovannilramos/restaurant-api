package br.com.sinuqueiros.restaurant.services.order;

import br.com.sinuqueiros.restaurant.enums.OrderStatusEnum;
import br.com.sinuqueiros.restaurant.services.item.CreateItemService;
import br.com.sinuqueiros.restaurant.services.order.dto.OrderDTO;
import br.com.sinuqueiros.restaurant.services.order.providers.OrderRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.sinuqueiros.restaurant.config.cache.OrderListCache.addOrderResponseItem;
import static br.com.sinuqueiros.restaurant.config.cache.OrderListCache.getOrderResponseList;
import static br.com.sinuqueiros.restaurant.services.order.helper.OrderHelper.calculateTotal;

@Service
@RequiredArgsConstructor
public class CreateOrderService implements SendToWebsocket {
    private final OrderRepositoryProvider orderRepositoryProvider;
    private final CreateItemService createItemService;
    private final SimpMessagingTemplate messagingTemplate;

    @Transactional
    public void createOrder(final OrderDTO orderDTO) {
        final var itemDTOList = createItemService.save(orderDTO.getItems());
        orderDTO.setItems(itemDTOList);
        orderDTO.setTotal(calculateTotal(orderDTO));
        orderDTO.setStatus(OrderStatusEnum.REQUESTED);
        final var orderDTOSaved = orderRepositoryProvider.save(orderDTO);
        sendUpdatedListToWebsocket(orderDTOSaved);
    }

    @Override
    public void sendUpdatedListToWebsocket(final OrderDTO orderDTO) {
        addOrderResponseItem(orderDTO);
        messagingTemplate.convertAndSend("/topic/order", getOrderResponseList());
    }
}



