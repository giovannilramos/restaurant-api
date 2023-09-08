package br.com.sinuqueiros.restaurant.services.order;

import br.com.sinuqueiros.restaurant.enums.OrderStatusEnum;
import br.com.sinuqueiros.restaurant.exceptions.BadRequestException;
import br.com.sinuqueiros.restaurant.exceptions.NotFoundException;
import br.com.sinuqueiros.restaurant.services.item.CreateItemService;
import br.com.sinuqueiros.restaurant.services.item.providers.ItemRepositoryProvider;
import br.com.sinuqueiros.restaurant.services.order.dto.OrderDTO;
import br.com.sinuqueiros.restaurant.services.order.providers.OrderRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.sinuqueiros.restaurant.config.cache.OrderListCache.getOrderResponseList;
import static br.com.sinuqueiros.restaurant.config.cache.OrderListCache.updateOrderResponseItem;
import static br.com.sinuqueiros.restaurant.services.order.helper.OrderHelper.calculateTotal;

@Service
@RequiredArgsConstructor
public class UpdateOrderItemsService implements SendToWebsocket {
    private final OrderRepositoryProvider orderRepositoryProvider;
    private final ItemRepositoryProvider itemRepositoryProvider;
    private final CreateItemService createItemService;
    private final SimpMessagingTemplate messagingTemplate;

    @Transactional
    public OrderDTO update(final Long id, final OrderDTO orderDTORequest) {
        final var orderDTO = orderRepositoryProvider.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found!"));
        if (!orderDTO.getStatus().equals(OrderStatusEnum.REQUESTED)) {
            throw new BadRequestException("""
                    Order cannot be changed at status "%s"!
                    """.formatted(orderDTO.getStatus().name().toLowerCase()));
        }
        final var itemDTOList = createItemService.save(orderDTORequest.getItems());
        orderDTO.getItems().forEach(itemRepositoryProvider::delete);
        orderDTO.setItems(itemDTOList);
        orderDTO.setTotal(calculateTotal(orderDTO));
        final var orderDTOSaved = orderRepositoryProvider.save(orderDTO);
        sendUpdatedListToWebsocket(orderDTOSaved);
        return orderDTOSaved;
    }

    @Override
    public void sendUpdatedListToWebsocket(final OrderDTO orderDTO) {
        updateOrderResponseItem(orderDTO);
        messagingTemplate.convertAndSend("/topic/order", getOrderResponseList());
    }
}
