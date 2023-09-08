package br.com.sinuqueiros.restaurant.services.order;

import br.com.sinuqueiros.restaurant.exceptions.NotFoundException;
import br.com.sinuqueiros.restaurant.queue.publishers.RabbitMQProducer;
import br.com.sinuqueiros.restaurant.services.order.dto.OrderDTO;
import br.com.sinuqueiros.restaurant.services.product.providers.ProductRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestOrderService {
    private final ProductRepositoryProvider productRepositoryProvider;
    private final RabbitMQProducer rabbitMQProducer;

    public void requestOrder(final OrderDTO orderDTO) {
        if (orderDTO.getItems().stream().anyMatch(itemDTO -> !Boolean.TRUE.equals(productRepositoryProvider.existsById(itemDTO.getProduct().getId())))) {
            throw new NotFoundException("Product not found!");
        }

        rabbitMQProducer.sendMessage(orderDTO);
    }
}



