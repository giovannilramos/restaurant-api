package br.com.sinuqueiros.restaurant.services.order;

import br.com.sinuqueiros.restaurant.services.order.dto.OrderDTO;
import br.com.sinuqueiros.restaurant.services.order.providers.OrderRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class ListOrderService {
    private final OrderRepositoryProvider orderRepositoryProvider;
    public List<OrderDTO> listOrder(final Integer tableNumber) {
        if (nonNull(tableNumber)) {
            return orderRepositoryProvider.findAllByTableNumber(tableNumber);
        }

        return orderRepositoryProvider.findAll();
    }
}



