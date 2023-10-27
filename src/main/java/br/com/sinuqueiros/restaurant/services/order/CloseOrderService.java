package br.com.sinuqueiros.restaurant.services.order;

import br.com.sinuqueiros.restaurant.services.item.dto.ItemDTO;
import br.com.sinuqueiros.restaurant.services.order.dto.OrderDTO;
import br.com.sinuqueiros.restaurant.services.order.providers.OrderRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CloseOrderService {
    private final OrderRepositoryProvider orderRepositoryProvider;

    public OrderDTO closeOrder(final Integer tableNumber) {
        final var orderDTOList = orderRepositoryProvider.findAllByTableNumber(tableNumber);

        final var itemDTOList = new ArrayList<ItemDTO>();
        orderDTOList.forEach(orderDTO -> orderDTO.getItems().forEach(item -> itemDTOList::add));

        final var total = orderDTOList.stream().map(orderDTO -> orderDTO.getTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);

        final var orderDTO = OrderDTO.builder().tableNumber(tableNumber).items(itemDTOList).total(total).build();

        orderRepositoryProvider.deleteAll(orderDTOList);

        return orderDTO;
    }
}
