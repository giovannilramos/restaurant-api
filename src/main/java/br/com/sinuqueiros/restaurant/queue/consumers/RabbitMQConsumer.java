package br.com.sinuqueiros.restaurant.queue.consumers;

import br.com.sinuqueiros.restaurant.services.order.CreateOrderService;
import br.com.sinuqueiros.restaurant.services.order.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQConsumer {
    private final CreateOrderService createOrderService;
    @RabbitListener(queues = {"${rabbitmq-config.queue}"})
    public void consumeMessage(final OrderDTO orderDTO) {
        log.info("Received message: {}", orderDTO.toString());
        createOrderService.createOrder(orderDTO);
    }
}
