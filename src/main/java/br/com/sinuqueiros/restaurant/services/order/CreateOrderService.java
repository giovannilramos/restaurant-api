package br.com.sinuqueiros.restaurant.services.order;

import br.com.sinuqueiros.restaurant.services.order.dto.OrderDTO;
import br.com.sinuqueiros.restaurant.services.order.providers.OrderRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateOrderService {
    private final OrderRepositoryProvider orderRepositoryProvider;

    public void createOrder(final OrderDTO orderDTO) {
        orderRepositoryProvider.save(orderDTO);
    }
}



