package br.com.sinuqueiros.restaurant.services.order;

import br.com.sinuqueiros.restaurant.services.order.dto.OrderDTO;
import br.com.sinuqueiros.restaurant.services.order.providers.OrderRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListOrderService {
    private final OrderRepositoryProvider orderRepositoryProvider;
    public List<OrderDTO> listOrder() {
        return orderRepositoryProvider.findAll();
    }
}



