package br.com.sinuqueiros.restaurant.config.cache;

import br.com.sinuqueiros.restaurant.services.order.providers.OrderRepositoryProvider;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OrderListLoadItems extends OrderListCache {
    private final OrderRepositoryProvider orderRepositoryProvider;

    @PostConstruct
    public void loadOrderListItems() {
        final var orderRepositoryProviderFindAll = orderRepositoryProvider.findAll();
        orderDTOList.addAll(orderRepositoryProviderFindAll);
    }
}
