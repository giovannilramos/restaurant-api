package br.com.sinuqueiros.restaurant.services.order;

import br.com.sinuqueiros.restaurant.exceptions.NotFoundException;
import br.com.sinuqueiros.restaurant.services.order.dto.OrderDTO;
import br.com.sinuqueiros.restaurant.services.order.providers.OrderRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetOrderByIdService {
    private final OrderRepositoryProvider orderRepositoryProvider;

    public OrderDTO getById(final Long id) {
        return orderRepositoryProvider.findById(id).orElseThrow(() -> new NotFoundException("Order not found!"));
    }
}
